import request from '@/utils/request'

// 查询产品单元列表
export function listSku(query) {
  return request({
    url: '/system/sku/list',
    method: 'get',
    params: query
  })
}

// 查询产品单元详细
export function getSku(sid) {
  return request({
    url: '/system/sku/' + sid,
    method: 'get'
  })
}

// 新增产品单元
export function addSku(data) {
  return request({
    url: '/system/sku',
    method: 'post',
    data: data
  })
}

// 修改产品单元
export function updateSku(data) {
  return request({
    url: '/system/sku',
    method: 'put',
    data: data
  })
}

// 删除产品单元
export function delSku(sid) {
  return request({
    url: '/system/sku/' + sid,
    method: 'delete'
  })
}
