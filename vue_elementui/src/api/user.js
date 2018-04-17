import request from '@/utils/request'

export function getUserList(data) {
  return request({
    url: '/user/list',
    method: 'post',
    data
  })
}

export function addUser(data) {
  return request({
    url: '/user/addUser',
    method: 'post',
    data
  })
}

export function updateUser(data) {
  return request({
    url: '/user/updateUser',
    method: 'post',
    data
  })
}

export function deleteUser(data) {
  return request({
    url: '/user/deleteUser',
    method: 'post',
    data
  })
}
