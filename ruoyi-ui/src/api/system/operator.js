import request from '@/utils/request'

// 查询操作员列表
export function listOperator(query) {
  return request({
    url: '/system/operator/list',
    method: 'get',
    params: query
  })
}

// 查询操作员详细
export function getOperator(sid) {
  return request({
    url: '/system/operator/' + sid,
    method: 'get'
  })
}

// 新增操作员
export function addOperator(data) {
  return request({
    url: '/system/operator',
    method: 'post',
    data: data
  })
}

// 修改操作员
export function updateOperator(data) {
  return request({
    url: '/system/operator',
    method: 'put',
    data: data
  })
}

// 删除操作员
export function delOperator(sid) {
  return request({
    url: '/system/operator/' + sid,
    method: 'delete'
  })
}
