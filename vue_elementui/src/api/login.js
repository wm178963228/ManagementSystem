import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/user/login',
    method: 'post',
    data: JSON.stringify({
      'username': username,
      'password': password
    })
  })
}

export function getInfo(token) {
  return request({
    url: '/user/getInfo',
    method: 'post',
    data: JSON.stringify({
      'token': token
    })
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
