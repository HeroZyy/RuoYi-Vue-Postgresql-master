import request from '@/utils/request'

// 查询推荐模块列表
export function listModule(query) {
  return request({
    url: '/system/module/list',
    method: 'get',
    params: query
  })
}

// 查询推荐模块详细
export function getModule(sid) {
  return request({
    url: '/system/module/' + sid,
    method: 'get'
  })
}

// 新增推荐模块
export function addModule(data) {
  return request({
    url: '/system/module',
    method: 'post',
    data: data
  })
}

// 修改推荐模块
export function updateModule(data) {
  return request({
    url: '/system/module',
    method: 'put',
    data: data
  })
}

// 删除推荐模块
export function delModule(sid) {
  return request({
    url: '/system/module/' + sid,
    method: 'delete'
  })
}
