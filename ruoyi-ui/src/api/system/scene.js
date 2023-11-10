import request from '@/utils/request'

// 查询场景模块列表
export function listScene(query) {
  return request({
    url: '/system/scene/list',
    method: 'get',
    params: query
  })
}

// 查询场景模块详细
export function getScene(sid) {
  return request({
    url: '/system/scene/' + sid,
    method: 'get'
  })
}

// 新增场景模块
export function addScene(data) {
  return request({
    url: '/system/scene',
    method: 'post',
    data: data
  })
}

// 修改场景模块
export function updateScene(data) {
  return request({
    url: '/system/scene',
    method: 'put',
    data: data
  })
}

// 删除场景模块
export function delScene(sid) {
  return request({
    url: '/system/scene/' + sid,
    method: 'delete'
  })
}
