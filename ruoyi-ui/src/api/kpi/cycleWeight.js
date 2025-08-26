import request from '@/utils/request'

export function listCycleWeight(query) {
  return request({ url: '/kpi/cycleWeight/list', method: 'get', params: query })
}
export function getCycleWeight(id) {
  return request({ url: '/kpi/cycleWeight/' + id, method: 'get' })
}
export function addCycleWeight(data) {
  return request({ url: '/kpi/cycleWeight', method: 'post', data })
}
export function updateCycleWeight(data) {
  return request({ url: '/kpi/cycleWeight', method: 'put', data })
}
export function delCycleWeight(ids) {
  return request({ url: '/kpi/cycleWeight/' + ids, method: 'delete' })
}
export function exportCycleWeight(query) {
  return request({ url: '/kpi/cycleWeight/export', method: 'post', data: query, responseType: 'blob' })
}
