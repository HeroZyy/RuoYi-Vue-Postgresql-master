import request from '@/utils/request'

// 查询产品收藏列表
export function listFavorite(query) {
  return request({
    url: '/system/favorite/list',
    method: 'get',
    params: query
  })
}

// 查询产品收藏详细
export function getFavorite(sid) {
  return request({
    url: '/system/favorite/' + sid,
    method: 'get'
  })
}

// 新增产品收藏
export function addFavorite(data) {
  return request({
    url: '/system/favorite',
    method: 'post',
    data: data
  })
}

// 修改产品收藏
export function updateFavorite(data) {
  return request({
    url: '/system/favorite',
    method: 'put',
    data: data
  })
}

// 删除产品收藏
export function delFavorite(sid) {
  return request({
    url: '/system/favorite/' + sid,
    method: 'delete'
  })
}
