import request from '@/utils/request'

export function listEvalTask(query) { return request({ url: '/kpi/evalTask/list', method: 'get', params: query }) }
export function listMyEvalTask(query) { return request({ url: '/kpi/evalTask/my', method: 'get', params: query }) }
export function getEvalTask(id) { return request({ url: '/kpi/evalTask/' + id, method: 'get' }) }
export function addEvalTask(data) { return request({ url: '/kpi/evalTask', method: 'post', data }) }
export function updateEvalTask(data) { return request({ url: '/kpi/evalTask', method: 'put', data }) }
export function delEvalTask(ids) { return request({ url: '/kpi/evalTask/' + ids, method: 'delete' }) }
export function exportEvalTask(query) { return request({ url: '/kpi/evalTask/export', method: 'post', data: query, responseType: 'blob' }) }

export function addScore(data) { return request({ url: '/kpi/score', method: 'post', data }) }
