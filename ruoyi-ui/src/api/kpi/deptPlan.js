import request from '@/utils/request'

export function listDeptPlan(query) { return request({ url: '/kpi/deptPlan/list', method: 'get', params: query }) }
export function getDeptPlan(id) { return request({ url: '/kpi/deptPlan/' + id, method: 'get' }) }
export function addDeptPlan(data) { return request({ url: '/kpi/deptPlan', method: 'post', data }) }
export function updateDeptPlan(data) { return request({ url: '/kpi/deptPlan', method: 'put', data }) }
export function delDeptPlan(ids) { return request({ url: '/kpi/deptPlan/' + ids, method: 'delete' }) }
export function exportDeptPlan(query) { return request({ url: '/kpi/deptPlan/export', method: 'post', data: query, responseType: 'blob' }) }
