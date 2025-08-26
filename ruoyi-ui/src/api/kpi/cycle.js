import request from '@/utils/request'

export function listCycle(query) {
  return request({ url: '/kpi/cycle/list', method: 'get', params: query })
}
export function getCycle(id) {
  return request({ url: '/kpi/cycle/' + id, method: 'get' })
}
export function addCycle(data) {
  return request({ url: '/kpi/cycle', method: 'post', data })
}
export function updateCycle(data) {
  return request({ url: '/kpi/cycle', method: 'put', data })
}
export function delCycle(ids) {
  return request({ url: '/kpi/cycle/' + ids, method: 'delete' })
}
export function exportCycle(query) {
  return request({ url: '/kpi/cycle/export', method: 'post', data: query, responseType: 'blob' })
}
