#!/usr/bin/env node

const fs = require('fs');
const fsp = require('fs/promises');
const path = require('path');
const http = require('http');
const https = require('https');
const { HttpProxyAgent } = require('http-proxy-agent');
const { HttpsProxyAgent } = require('https-proxy-agent');

function printUsageAndExit() {
	console.log(
		'用法:\n' +
		'  node markdown图片下载替换.js --md <markdown路径> --out <静态资源目录> [--publicPath <Markdown替换前缀>] [--startIndex <起始序号>] [--proxy <http(s)://HOST:PORT>]\n' +
		'说明:\n' +
		'  --md         必填, 要处理的 Markdown 文件路径\n' +
		'  --out        必填, 下载图片保存到的目录(若不存在会创建)\n' +
		'  --publicPath 选填, Markdown 中替换后的前缀(如 /static/img/).\n' +
		'               若不提供, 将自动计算相对路径(从 Markdown 所在目录到静态目录).\n' +
		'  --startIndex 选填, 序号起始值, 默认 1\n' +
		'  --proxy      选填, 通过代理下载, 例如 http://127.0.0.1:8443\n' +
		'               也可用环境变量 HTTPS_PROXY/HTTP_PROXY 指定\n' +
		'示例:\n' +
		'  node markdown图片下载替换.js --md "AI项目开发教程/01-开发工具安装/03.开发工具-navicat安装.md" --out "AI项目开发教程/01-开发工具安装/03assets" --proxy http://127.0.0.1:8443\n' +
		'  node markdown图片下载替换.js --md "./doc.md" --out "./public/img" --publicPath "/img/" --startIndex 1' 
	);
	process.exit(1);
}

function parseArgs(argv) {
	const args = argv.slice(2);
	const out = { positionals: [] };
	for (let i = 0; i < args.length; i++) {
		const cur = args[i];
		if (cur.startsWith('--')) {
			const key = cur.slice(2);
			const next = args[i + 1];
			if (next && !next.startsWith('--')) {
				out[key] = next;
				i++;
			} else {
				out[key] = true;
			}
		} else {
			out.positionals.push(cur);
		}
	}
	// 兼容位置参数: <md> <out>
	if (!out.md && out.positionals[0]) out.md = out.positionals[0];
	if (!out.out && out.positionals[1]) out.out = out.positionals[1];
	return out;
}

function isHttpUrl(u) {
	return /^https?:\/\//i.test(u);
}

function stripAngleBrackets(s) {
	if (!s) return s;
	s = s.trim();
	if (s.startsWith('<') && s.endsWith('>')) return s.slice(1, -1);
	return s;
}

function ensureForwardSlashes(p) {
	return p.replace(/\\/g, '/');
}

async function ensureDir(dir) {
	await fsp.mkdir(dir, { recursive: true });
}

function getExtFromUrl(urlStr) {
	try {
		const u = new URL(urlStr);
		const ext = path.extname(u.pathname);
		if (ext) return ext;
		return '.png';
	} catch {
		return '.png';
	}
}

function getHttpModule(urlStr) {
	return urlStr.startsWith('https') ? https : http;
}

function createAgents(proxyUrl) {
	if (!proxyUrl) return { httpAgent: undefined, httpsAgent: undefined };
	try {
		return {
			httpAgent: new HttpProxyAgent(proxyUrl),
			httpsAgent: new HttpsProxyAgent(proxyUrl),
		};
	} catch {
		return { httpAgent: undefined, httpsAgent: undefined };
	}
}

function downloadWithRedirects(urlStr, destPath, maxRedirects = 5, timeoutMs = 20000, agents = {}) {
	return new Promise((resolve, reject) => {
		let redirects = 0;

		function doRequest(currentUrl) {
			const lib = getHttpModule(currentUrl);
			const isHttps = currentUrl.startsWith('https');
			const agent = isHttps ? agents.httpsAgent : agents.httpAgent;
			const req = lib.get(currentUrl, { headers: { 'User-Agent': 'Mozilla/5.0' }, agent }, (res) => {
				if (res.statusCode >= 300 && res.statusCode < 400 && res.headers.location) {
					if (redirects >= maxRedirects) {
						res.resume();
						return reject(new Error('重定向过多: ' + currentUrl));
					}
					redirects++;
					const nextUrl = new URL(res.headers.location, currentUrl).toString();
					res.resume();
					return doRequest(nextUrl);
				}
				if (res.statusCode < 200 || res.statusCode >= 300) {
					res.resume();
					return reject(new Error('下载失败, 状态码: ' + res.statusCode + ' - ' + currentUrl));
				}
				const file = fs.createWriteStream(destPath);
				res.pipe(file);
				file.on('finish', () => {
					file.close(() => resolve());
				});
				file.on('error', (err) => {
					try { fs.unlinkSync(destPath); } catch {}
					reject(err);
				});
			}).on('error', reject);
			req.setTimeout(timeoutMs, () => {
				req.destroy(new Error('请求超时: ' + currentUrl));
			});
		}

		doRequest(urlStr);
	});
}

async function fileExists(p) {
	try {
		await fsp.access(p);
		return true;
	} catch {
		return false;
	}
}

async function computeNextFilename(dir, basePrefix, startIndex, ext) {
	let idx = startIndex;
	while (true) {
		const name = `${basePrefix}${idx}${ext}`;
		const full = path.join(dir, name);
		if (!(await fileExists(full))) return { name, full, idx };
		idx++;
	}
}

function collectExternalImageUrlsInOrder(markdownContent) {
	const imageRegex = /!\[[^\]]*\]\(([^)]+)\)/g;
	const urls = [];
	let m;
	while ((m = imageRegex.exec(markdownContent)) !== null) {
		const inside = m[1];
		let firstToken = inside.trim();
		// 处理含有 title 的情况: <url> "title"
		if (/\s/.test(firstToken)) {
			firstToken = firstToken.split(/\s+/)[0];
		}
		firstToken = stripAngleBrackets(firstToken);
		if (isHttpUrl(firstToken)) {
			urls.push({ raw: firstToken, matchStart: m.index, matchEnd: m.index + m[0].length, inside });
		}
	}
	return urls;
}

async function main() {
	const args = parseArgs(process.argv);
	const mdPath = args.md;
	const outDir = args.out;
	const publicPath = args.publicPath; // 如果提供, 直接使用
	const startIndex = Number(args.startIndex || 1);
	const proxyUrl = args.proxy || process.env.HTTPS_PROXY || process.env.HTTP_PROXY || process.env.https_proxy || process.env.http_proxy;
	const agents = createAgents(proxyUrl);
	if (proxyUrl) {
		console.log('使用代理:', proxyUrl);
	}

	if (!mdPath || !outDir) {
		printUsageAndExit();
	}

	const mdAbs = path.resolve(mdPath);
	const outAbs = path.resolve(outDir);
	await ensureDir(outAbs);

	let mdContent;
	try {
		mdContent = await fsp.readFile(mdAbs, 'utf8');
	} catch (e) {
		console.error('读取 Markdown 文件失败:', e.message);
		process.exit(2);
	}

	const backupCandidate = mdAbs + '.bak';
	let backupPath = backupCandidate;
	if (await fileExists(backupCandidate)) {
		backupPath = mdAbs + '.' + Date.now() + '.bak';
	}
	await fsp.copyFile(mdAbs, backupPath);
	console.log('已生成备份:', ensureForwardSlashes(path.relative(process.cwd(), backupPath)));

	const images = collectExternalImageUrlsInOrder(mdContent);
	if (images.length === 0) {
		console.log('未找到外链图片, 无需处理。');
		return;
	}

	console.log('检测到外链图片数量:', images.length);

	const urlToLocalName = new Map();
	let currentIndex = startIndex;

	for (const item of images) {
		const urlStr = item.raw;
		if (urlToLocalName.has(urlStr)) continue;
		const ext = getExtFromUrl(urlStr).toLowerCase();
		const { name, full, idx } = await computeNextFilename(outAbs, 'img_', currentIndex, ext);
		currentIndex = idx + 1;
		console.log(`下载: ${urlStr} -> ${ensureForwardSlashes(path.relative(process.cwd(), full))}`);
		try {
			await downloadWithRedirects(urlStr, full, 5, 20000, agents);
			urlToLocalName.set(urlStr, name);
		} catch (e) {
			console.error('下载失败, 将跳过此图片:', urlStr, '\n原因:', e.message);
		}
	}

	// 构建替换
	const mdDir = path.dirname(mdAbs);
	let replacementPrefix;
	if (publicPath) {
		replacementPrefix = publicPath.endsWith('/') ? publicPath : publicPath + '/';
	} else {
		const rel = path.relative(mdDir, outAbs);
		replacementPrefix = ensureForwardSlashes(rel) + (rel ? '/' : '');
	}

	const imageRegex = /(!\[[^\]]*\]\()([^)]+)(\))/g;
	const newContent = mdContent.replace(imageRegex, (fullMatch, pre, inside, post) => {
		let insideTrim = inside.trim();
		let urlPart = insideTrim;
		let suffix = '';
		if (/\s/.test(insideTrim)) {
			const first = insideTrim.split(/\s+/)[0];
			suffix = insideTrim.slice(first.length);
			urlPart = first;
		}
		urlPart = stripAngleBrackets(urlPart);
		if (!isHttpUrl(urlPart)) {
			return fullMatch; // 非外链不处理
		}
		const mapped = urlToLocalName.get(urlPart);
		if (!mapped) return fullMatch; // 下载失败未映射
		const replacedUrl = replacementPrefix + mapped;
		const newInside = `${replacedUrl}${suffix}`;
		return pre + newInside + post;
	});

	if (newContent !== mdContent) {
		await fsp.writeFile(mdAbs, newContent, 'utf8');
		console.log('已完成替换并写回 Markdown。');
	} else {
		console.log('内容无变更。');
	}

	console.log('处理完成。');
}

main().catch((e) => {
	console.error('执行出错:', e);
	process.exit(1);
}); 