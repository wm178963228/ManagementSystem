import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
 **/
export const constantRouterMap = [
  {path: '/login', component: () => import('@/views/login/index'), hidden: true},
  {path: '/404', component: () => import('@/views/404'), hidden: true},
  {
    path: '',
    redirect: '/dashboard',
    component: Layout,
    children: [{
      path: 'dashboard',
      name: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: {title: 'XX管理系统主页', icon: 'dashboard'}
    }]
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '/example',
    component: Layout,
    redirect: 'noredirect',
    name: 'Example',
    meta: {title: '系统管理', icon: 'example'},
    children: [
      {
        path: 'roletable',
        name: 'RoleTable',
        component: () => import('@/views/roletable/index'),
        meta: {roles: ['operator'], title: '角色管理', icon: 'table'}
      }, {
        path: 'usertable',
        name: 'UserTable',
        component: () => import('@/views/usertable/index'),
        meta: {roles: ['operator'], title: '用户管理', icon: 'table'}
      }
    ]
  },
  {
    path: '',
    component: Layout,
    children: [
      {
        path: 'form',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: {title: 'Form', icon: 'form'}
      }
    ]
  }, {
    path: '',
    component: Layout,
    children: [
      {
        path: 'test',
        name: 'Test',
        component: () => import('@/views/test/index'),
        meta: {title: 'VueTest', icon: 'test'}
      }
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]

