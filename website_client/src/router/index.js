import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决编程式路由往同一地址跳转时会报错的情况
const originalPush = VueRouter.prototype.push;
const originalReplace = VueRouter.prototype.replace;

// push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch(err => err);
};

//replace
VueRouter.prototype.replace = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalReplace.call(this, location, onResolve, onReject);
  return originalReplace.call(this, location).catch(err => err);
};


const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import('../views/login/Login')
    // {path:'/', redirect:'/home'},
  },
  {
    path: '/appHome',
    name: 'appHome',
    component: () => import('../views/AppHome'),
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: 'home',
        component: () => import('../components/home/Home'),
      },
      {
        path: '/myMusic',
        name: 'myMusic',
        component: () => import('../components/myMusic/MyMusic')
      },
      {
        path: '/perInfo',
        name: 'perInfo',
        component: () => import('../components/personal/PerInfo')
      },
      {
        path: '/songList',
        name: 'songList',
        component: () => import('../components/home/SongList'),
      },
      {
        path: '/singer',
        name: 'singer',
        component: () => import('@/components/singer/Singer'),
      },
      {
        path: '/singerDetail',
        name: 'singerDetail',
        component: () => import('@/components/singer/SingerDetail')
      },
      {
        path: '/player',
        name: 'player',
        component: () => import('@/components/Player')
      },
    ]
  },




]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
