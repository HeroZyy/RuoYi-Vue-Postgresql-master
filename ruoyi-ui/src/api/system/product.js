import request from '@/utils/request'

// 查询产品基本信息列表
export function listProduct(query) {
  return request({
    url: '/system/product/list',
    method: 'get',
    params: query
  })
}

// 查询产品基本信息详细
export function getProduct(sid) {
  return request({
    url: '/system/product/' + sid,
    method: 'get'
  })
}

// 新增产品基本信息
export function addProduct(data) {
  return request({
    url: '/system/product',
    method: 'post',
    data: data
  })
}

// 修改产品基本信息
export function updateProduct(data) {
  return request({
    url: '/system/product',
    method: 'put',
    data: data
  })
}

// 删除产品基本信息
export function delProduct(sid) {
  return request({
    url: '/system/product/' + sid,
    method: 'delete'
  })
}
