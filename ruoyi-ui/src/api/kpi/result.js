import request from '@/utils/request'

export function listResult(query) { return request({ url: '/kpi/result/list', method: 'get', params: query }) }
export function getResult(id) { return request({ url: '/kpi/result/' + id, method: 'get' }) }
export function exportResult(query) { return request({ url: '/kpi/result/export', method: 'post', data: query, responseType: 'blob' }) }

export function signResult(resultId) { return request({ url: '/kpi/resultSign/sign/' + resultId, method: 'post' }) }
