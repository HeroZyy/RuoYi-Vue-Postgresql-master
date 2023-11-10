import request from '@/utils/request'

// 查询产品文件列表
export function listPfiles(query) {
  return request({
    url: '/system/pfiles/list',
    method: 'get',
    params: query
  })
}

// 查询产品文件详细
export function getPfiles(sid) {
  return request({
    url: '/system/pfiles/' + sid,
    method: 'get'
  })
}

// 新增产品文件
export function addPfiles(data) {
  return request({
    url: '/system/pfiles',
    method: 'post',
    data: data
  })
}

// 修改产品文件
export function updatePfiles(data) {
  return request({
    url: '/system/pfiles',
    method: 'put',
    data: data
  })
}

// 删除产品文件
export function delPfiles(sid) {
  return request({
    url: '/system/pfiles/' + sid,
    method: 'delete'
  })
}
