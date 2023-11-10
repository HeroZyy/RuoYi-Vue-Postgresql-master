import request from '@/utils/request'

// 查询场景关联列表
export function listRelation(query) {
  return request({
    url: '/system/relation/list',
    method: 'get',
    params: query
  })
}

// 查询场景关联详细
export function getRelation(sid) {
  return request({
    url: '/system/relation/' + sid,
    method: 'get'
  })
}

// 新增场景关联
export function addRelation(data) {
  return request({
    url: '/system/relation',
    method: 'post',
    data: data
  })
}

// 修改场景关联
export function updateRelation(data) {
  return request({
    url: '/system/relation',
    method: 'put',
    data: data
  })
}

// 删除场景关联
export function delRelation(sid) {
  return request({
    url: '/system/relation/' + sid,
    method: 'delete'
  })
}
