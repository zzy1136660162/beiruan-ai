import request from '@/utils/request'

export function listIndicatorCategory(query) {
  return request({
    url: '/kpi/indicatorCategory/list',
    method: 'get',
    params: query
  })
}

export function getIndicatorCategory(id) {
  return request({
    url: '/kpi/indicatorCategory/' + id,
    method: 'get'
  })
}

export function addIndicatorCategory(data) {
  return request({
    url: '/kpi/indicatorCategory',
    method: 'post',
    data: data
  })
}

export function updateIndicatorCategory(data) {
  return request({
    url: '/kpi/indicatorCategory',
    method: 'put',
    data: data
  })
}

export function delIndicatorCategory(ids) {
  return request({
    url: '/kpi/indicatorCategory/' + ids,
    method: 'delete'
  })
}

export function exportIndicatorCategory(query) {
  return request({
    url: '/kpi/indicatorCategory/export',
    method: 'post',
    data: query,
    responseType: 'blob'
  })
}
