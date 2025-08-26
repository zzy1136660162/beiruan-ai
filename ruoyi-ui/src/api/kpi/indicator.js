import request from '@/utils/request'

export function listIndicator(query) {
  return request({
    url: '/kpi/indicator/list',
    method: 'get',
    params: query
  })
}

export function getIndicator(id) {
  return request({
    url: '/kpi/indicator/' + id,
    method: 'get'
  })
}

export function addIndicator(data) {
  return request({
    url: '/kpi/indicator',
    method: 'post',
    data: data
  })
}

export function updateIndicator(data) {
  return request({
    url: '/kpi/indicator',
    method: 'put',
    data: data
  })
}

export function delIndicator(ids) {
  return request({
    url: '/kpi/indicator/' + ids,
    method: 'delete'
  })
}

export function exportIndicator(query) {
  return request({
    url: '/kpi/indicator/export',
    method: 'post',
    data: query,
    responseType: 'blob'
  })
}
