-- KPI 模块字典初始化（若依内置 sys_dict_*）
-- 如已存在相同 dict_type，请根据现网调整 code 与排序

-- 指标种类
INSERT INTO sys_dict_type(dict_id, dict_name, dict_type, status, create_by, create_time, remark)
SELECT 20001, 'KPI 指标种类', 'kpi_kind', '0', 'admin', NOW(), 'dept/person/middle'
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_type WHERE dict_type = 'kpi_kind');

INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, remark)
SELECT 2000101, 1, '部门', 'dept', 'kpi_kind', NULL, 'default', 'N', '0', 'admin', NOW(), ''
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_kind' AND dict_value='dept');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, remark)
SELECT 2000102, 2, '个人', 'person', 'kpi_kind', NULL, 'default', 'N', '0', 'admin', NOW(), ''
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_kind' AND dict_value='person');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default, status, create_by, create_time, remark)
SELECT 2000103, 3, '中层', 'middle', 'kpi_kind', NULL, 'default', 'N', '0', 'admin', NOW(), ''
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_kind' AND dict_value='middle');

-- 周期类型
INSERT INTO sys_dict_type(dict_id, dict_name, dict_type, status, create_by, create_time, remark)
SELECT 20002, 'KPI 周期类型', 'kpi_cycle_type', '0', 'admin', NOW(), 'quarter/half/year/custom'
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_type WHERE dict_type = 'kpi_cycle_type');

INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000201, 1, '季度', 'quarter', 'kpi_cycle_type', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_cycle_type' AND dict_value='quarter');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000202, 2, '半年', 'half', 'kpi_cycle_type', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_cycle_type' AND dict_value='half');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000203, 3, '年度', 'year', 'kpi_cycle_type', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_cycle_type' AND dict_value='year');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000204, 4, '自定义', 'custom', 'kpi_cycle_type', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_cycle_type' AND dict_value='custom');

-- 方案状态
INSERT INTO sys_dict_type(dict_id, dict_name, dict_type, status, create_by, create_time, remark)
SELECT 20003, 'KPI 方案状态', 'kpi_status', '0', 'admin', NOW(), 'draft/pending/approved/rejected/active/finished'
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_type WHERE dict_type='kpi_status');

INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000301, 1, '草稿', 'draft', 'kpi_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_status' AND dict_value='draft');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000302, 2, '待审', 'pending', 'kpi_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_status' AND dict_value='pending');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000303, 3, '通过', 'approved', 'kpi_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_status' AND dict_value='approved');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000304, 4, '驳回', 'rejected', 'kpi_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_status' AND dict_value='rejected');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000305, 5, '进行中', 'active', 'kpi_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_status' AND dict_value='active');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000306, 6, '已结束', 'finished', 'kpi_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_status' AND dict_value='finished');

-- 任务状态
INSERT INTO sys_dict_type(dict_id, dict_name, dict_type, status, create_by, create_time, remark)
SELECT 20004, 'KPI 任务状态', 'kpi_task_status', '0', 'admin', NOW(), 'pending/done/expired'
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_type WHERE dict_type='kpi_task_status');

INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000401, 1, '待完成', 'pending', 'kpi_task_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_task_status' AND dict_value='pending');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000402, 2, '已完成', 'done', 'kpi_task_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_task_status' AND dict_value='done');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000403, 3, '已超期', 'expired', 'kpi_task_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_task_status' AND dict_value='expired');

-- 结果状态
INSERT INTO sys_dict_type(dict_id, dict_name, dict_type, status, create_by, create_time, remark)
SELECT 20005, 'KPI 结果状态', 'kpi_result_status', '0', 'admin', NOW(), 'generated/confirmed'
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_type WHERE dict_type='kpi_result_status');

INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000501, 1, '已生成', 'generated', 'kpi_result_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_result_status' AND dict_value='generated');
INSERT INTO sys_dict_data(dict_code, dict_sort, dict_label, dict_value, dict_type, is_default, status, create_by, create_time)
SELECT 2000502, 2, '已确认', 'confirmed', 'kpi_result_status', 'N', '0', 'admin', NOW()
WHERE NOT EXISTS (SELECT 1 FROM sys_dict_data WHERE dict_type='kpi_result_status' AND dict_value='confirmed'); 