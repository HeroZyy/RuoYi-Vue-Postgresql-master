import request from '@/utils/request'

// 查询产品描述
列表
export function listDesc(query) {
  return request({
    url: '/system/desc/list',
    method: 'get',
    params: query
  })
}

// 查询产品描述
详细
export function getDesc(sid) {
  return request({
    url: '/system/desc/' + sid,
    method: 'get'
  })
}

// 新增产品描述

export function addDesc(data) {
  return request({
    url: '/system/desc',
    method: 'post',
    data: data
  })
}

// 修改产品描述

export function updateDesc(data) {
  return request({
    url: '/system/desc',
    method: 'put',
    data: data
  })
}

// 删除产品描述

export function delDesc(sid) {
  return request({
    url: '/system/desc/' + sid,
    method: 'delete'
  })
}
