import request from '@/utils/request'

export function listPersonPlan(query) { return request({ url: '/kpi/personPlan/list', method: 'get', params: query }) }
export function getPersonPlan(id) { return request({ url: '/kpi/personPlan/' + id, method: 'get' }) }
export function addPersonPlan(data) { return request({ url: '/kpi/personPlan', method: 'post', data }) }
export function updatePersonPlan(data) { return request({ url: '/kpi/personPlan', method: 'put', data }) }
export function delPersonPlan(ids) { return request({ url: '/kpi/personPlan/' + ids, method: 'delete' }) }
export function exportPersonPlan(query) { return request({ url: '/kpi/personPlan/export', method: 'post', data: query, responseType: 'blob' }) }
