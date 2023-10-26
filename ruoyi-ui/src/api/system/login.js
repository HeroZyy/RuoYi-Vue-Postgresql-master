import request from '@/utils/request'

// 查询登录信息列表
export function listLogin(query) {
  return request({
    url: '/system/login/list',
    method: 'get',
    params: query
  })
}

// 查询登录信息详细
export function getLogin(sid) {
  return request({
    url: '/system/login/' + sid,
    method: 'get'
  })
}

// 新增登录信息
export function addLogin(data) {
  return request({
    url: '/system/login',
    method: 'post',
    data: data
  })
}

// 修改登录信息
export function updateLogin(data) {
  return request({
    url: '/system/login',
    method: 'put',
    data: data
  })
}

// 删除登录信息
export function delLogin(sid) {
  return request({
    url: '/system/login/' + sid,
    method: 'delete'
  })
}
