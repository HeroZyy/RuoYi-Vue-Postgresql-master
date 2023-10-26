import request from '@/utils/request'

// 查询产品资料下载信息列表
export function listDownload(query) {
  return request({
    url: '/system/download/list',
    method: 'get',
    params: query
  })
}

// 查询产品资料下载信息详细
export function getDownload(sid) {
  return request({
    url: '/system/download/' + sid,
    method: 'get'
  })
}

// 新增产品资料下载信息
export function addDownload(data) {
  return request({
    url: '/system/download',
    method: 'post',
    data: data
  })
}

// 修改产品资料下载信息
export function updateDownload(data) {
  return request({
    url: '/system/download',
    method: 'put',
    data: data
  })
}

// 删除产品资料下载信息
export function delDownload(sid) {
  return request({
    url: '/system/download/' + sid,
    method: 'delete'
  })
}
