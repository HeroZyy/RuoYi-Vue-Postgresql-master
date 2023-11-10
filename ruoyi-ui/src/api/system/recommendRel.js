import request from '@/utils/request'

// 查询推荐关联列表
export function listRecommendrel(query) {
  return request({
    url: '/system/recommendrel/list',
    method: 'get',
    params: query
  })
}

// 查询推荐关联详细
export function getRecommendrel(sid) {
  return request({
    url: '/system/recommendrel/' + sid,
    method: 'get'
  })
}

// 新增推荐关联
export function addRecommendrel(data) {
  return request({
    url: '/system/recommendrel',
    method: 'post',
    data: data
  })
}

// 修改推荐关联
export function updateRecommendrel(data) {
  return request({
    url: '/system/recommendrel',
    method: 'put',
    data: data
  })
}

// 删除推荐关联
export function delRecommendrel(sid) {
  return request({
    url: '/system/recommendrel/' + sid,
    method: 'delete'
  })
}
