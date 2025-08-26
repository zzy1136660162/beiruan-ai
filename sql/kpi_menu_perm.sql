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