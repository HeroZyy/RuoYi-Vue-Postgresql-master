import request from '@/utils/request'

// 查询分类模块列表
export function listClass(query) {
  return request({
    url: '/system/class/list',
    method: 'get',
    params: query
  })
}

// 查询分类模块详细
export function getClass(sid) {
  return request({
    url: '/system/class/' + sid,
    method: 'get'
  })
}

// 新增分类模块
export function addClass(data) {
  return request({
    url: '/system/class',
    method: 'post',
    data: data
  })
}

// 修改分类模块
export function updateClass(data) {
  return request({
    url: '/system/class',
    method: 'put',
    data: data
  })
}

// 删除分类模块
export function delClass(sid) {
  return request({
    url: '/system/class/' + sid,
    method: 'delete'
  })
}
