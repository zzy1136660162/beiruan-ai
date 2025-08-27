-- KPI 菜单与权限点
-- 父菜单：绩效管理
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21000, '绩效管理', 0, 10, 'kpi', 'Layout', 1, 'M', '0', '0', NULL, 'example', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21000);

-- 子菜单：指标分类
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21001, '指标分类', 21000, 1, 'indicatorCategory', 'kpi/indicatorCategory/index', 1, 'C', '0', '0', 'kpi:indicatorCategory:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21001);

-- 子菜单：指标库
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21002, '指标库', 21000, 2, 'indicator', 'kpi/indicator/index', 1, 'C', '0', '0', 'kpi:indicator:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21002);

-- 按钮权限：指标分类
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21003, '指标分类-查询', 21001, 1, '#', NULL, 1, 'F', '0', '0', 'kpi:indicatorCategory:query', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21003);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21004, '指标分类-新增', 21001, 2, '#', NULL, 1, 'F', '0', '0', 'kpi:indicatorCategory:add', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21004);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21005, '指标分类-修改', 21001, 3, '#', NULL, 1, 'F', '0', '0', 'kpi:indicatorCategory:edit', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21005);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21006, '指标分类-删除', 21001, 4, '#', NULL, 1, 'F', '0', '0', 'kpi:indicatorCategory:remove', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21006);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21007, '指标分类-导出', 21001, 5, '#', NULL, 1, 'F', '0', '0', 'kpi:indicatorCategory:export', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21007);

-- 按钮权限：指标库
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21008, '指标库-查询', 21002, 1, '#', NULL, 1, 'F', '0', '0', 'kpi:indicator:query', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21008);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21009, '指标库-新增', 21002, 2, '#', NULL, 1, 'F', '0', '0', 'kpi:indicator:add', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21009);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21010, '指标库-修改', 21002, 3, '#', NULL, 1, 'F', '0', '0', 'kpi:indicator:edit', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21010);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21011, '指标库-删除', 21002, 4, '#', NULL, 1, 'F', '0', '0', 'kpi:indicator:remove', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21011);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21012, '指标库-导出', 21002, 5, '#', NULL, 1, 'F', '0', '0', 'kpi:indicator:export', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21012); 

-- 考核周期 菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21013, '考核周期', 21000, 3, 'cycle', 'kpi/cycle/index', 1, 'C', '0', '0', 'kpi:cycle:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21013);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21014, '考核周期-查询', 21013, 1, 'F', 'kpi:cycle:query', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21014);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21015, '考核周期-新增', 21013, 2, 'F', 'kpi:cycle:add', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21015);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21016, '考核周期-修改', 21013, 3, 'F', 'kpi:cycle:edit', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21016);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21017, '考核周期-删除', 21013, 4, 'F', 'kpi:cycle:remove', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21017);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21018, '考核周期-导出', 21013, 5, 'F', 'kpi:cycle:export', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21018);

-- 周期权重 菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21019, '评分权重', 21000, 4, 'cycleWeight', 'kpi/cycleWeight/index', 1, 'C', '0', '0', 'kpi:cycleWeight:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21019);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21020, '评分权重-查询', 21019, 1, 'F', 'kpi:cycleWeight:query', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21020);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21021, '评分权重-新增', 21019, 2, 'F', 'kpi:cycleWeight:add', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21021);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21022, '评分权重-修改', 21019, 3, 'F', 'kpi:cycleWeight:edit', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21022);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21023, '评分权重-删除', 21019, 4, 'F', 'kpi:cycleWeight:remove', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21023);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21024, '评分权重-导出', 21019, 5, 'F', 'kpi:cycleWeight:export', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21024); 

-- 个人考核方案 菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21025, '个人考核方案', 21000, 5, 'personPlan', 'kpi/personPlan/index', 1, 'C', '0', '0', 'kpi:personPlan:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21025);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21026, '个人-查询', 21025, 1, 'F', 'kpi:personPlan:query', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21026);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21027, '个人-新增', 21025, 2, 'F', 'kpi:personPlan:add', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21027);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21028, '个人-修改', 21025, 3, 'F', 'kpi:personPlan:edit', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21028);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21029, '个人-删除', 21025, 4, 'F', 'kpi:personPlan:remove', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21029);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21030, '个人-导出', 21025, 5, 'F', 'kpi:personPlan:export', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21030);

-- 部门考核方案 菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21031, '部门考核方案', 21000, 6, 'deptPlan', 'kpi/deptPlan/index', 1, 'C', '0', '0', 'kpi:deptPlan:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21031);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21032, '部门-查询', 21031, 1, 'F', 'kpi:deptPlan:query', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21032);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21033, '部门-新增', 21031, 2, 'F', 'kpi:deptPlan:add', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21033);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21034, '部门-修改', 21031, 3, 'F', 'kpi:deptPlan:edit', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21034);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21035, '部门-删除', 21031, 4, 'F', 'kpi:deptPlan:remove', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21035);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21036, '部门-导出', 21031, 5, 'F', 'kpi:deptPlan:export', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21036); 

-- 评价任务 菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21037, '评价任务', 21000, 7, 'evalTask', 'kpi/evalTask/index', 1, 'C', '0', '0', 'kpi:evalTask:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21037);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21038, '评价任务-我的', 21037, 1, 'F', 'kpi:evalTask:my', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21038);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21039, '评价任务-查询', 21037, 2, 'F', 'kpi:evalTask:query', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21039);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21040, '评价任务-新增', 21037, 3, 'F', 'kpi:evalTask:add', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21040);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21041, '评价任务-修改', 21037, 4, 'F', 'kpi:evalTask:edit', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21041);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21042, '评价任务-删除', 21037, 5, 'F', 'kpi:evalTask:remove', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21042);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21043, '评价任务-导出', 21037, 6, 'F', 'kpi:evalTask:export', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21043);

-- 评分记录（用于权限控制评分接口）
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21044, '评分记录', 21000, 8, '#', NULL, 1, 'C', '1', '0', 'kpi:score:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21044);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21045, '评分-新增', 21044, 1, 'F', 'kpi:score:add', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21045);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21046, '评分-查询', 21044, 2, 'F', 'kpi:score:query', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21046);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21047, '评分-修改', 21044, 3, 'F', 'kpi:score:edit', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21047);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21048, '评分-删除', 21044, 4, 'F', 'kpi:score:remove', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21048);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21049, '评分-导出', 21044, 5, 'F', 'kpi:score:export', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21049); 

-- 考核结果 菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21050, '考核结果', 21000, 9, 'result', 'kpi/result/index', 1, 'C', '0', '0', 'kpi:result:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21050);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21051, '结果-查询', 21050, 1, 'F', 'kpi:result:query', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21051);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21052, '结果-导出', 21050, 2, 'F', 'kpi:result:export', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21052);

-- 结果签字（隐藏功能，用于权限控制）
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time)
SELECT 21053, '结果签字', 21000, 10, '#', NULL, 1, 'C', '1', '0', 'kpi:resultSign:list', '#', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21053);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21054, '签字-新增', 21053, 1, 'F', 'kpi:resultSign:add', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21054);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21055, '签字-查询', 21053, 2, 'F', 'kpi:resultSign:query', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21055);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21056, '签字-修改', 21053, 3, 'F', 'kpi:resultSign:edit', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21056);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21057, '签字-删除', 21053, 4, 'F', 'kpi:resultSign:remove', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21057);
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, menu_type, perms, create_by, create_time)
SELECT 21058, '签字-导出', 21053, 5, 'F', 'kpi:resultSign:export', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_menu WHERE menu_id=21058); 