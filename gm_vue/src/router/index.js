import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'

Vue.use(Router)

// //解决编程式路由往同一地址跳转时会报错的情况
// const originalPush = Router.prototype.push
// const originalReplace = Router.prototype.replace
// //push
// Router.prototype.push = function push(location, onResolve, onReject) {
//     if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
//     return originalPush.call(this, location).catch(err => err)
// }
// //replace
// Router.prototype.replace = function push(location, onResolve, onReject) {
//     if (onResolve || onReject) return originalReplace.call(this, location, onResolve, onReject)
//     return originalReplace.call(this, location).catch(err => err)
// }

const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            redirect: '/login',
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('@/components/Login'),
        },
        {
            path: '/home',
            name: 'home',
            component: () => import('@/components/home/Home'),
            meta: {
                requiresAuth: true
            },
            children: [
                {
                    path: '/home/createUser',
                    component: () => import('@/components/user/CreateUser'),
                }
            ]
        },
        {
            path: '/menu',
            name: 'menu',
            component: () => import('@/components/home/Menu'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '*',
            component: () => import('@/components/Error404')
        }

    ]
})

// let routes = []

// function initMenuRoutes() {
//     routes = []
//     menusToRoutes(store.getters.menus)
//     router.addRoutes(routes)
//     console.log("currentRouteFullPath1:"+router.currentRoute.fullPath)
//     router.options.routes.pop()
//
//     console.log("currentRouteFullPath2:"+router.currentRoute.fullPath)
//     console.log('router000000000000000000---------------' + JSON.stringify(router.options.routes))
//     // store.commit('initRoute', routes)
//     // routes = store.getters.routes
//     // if(!routes || routes.length <= 0){
//     //
//     // }else{
//     //     routes.forEach(route => {
//     //         console.log('=============' + JSON.stringify(route))
//     //         router.addRoute('home', route)
//     //     })
//     // }
// }
//
// function menusToRoutes(menus) {
//     menus.forEach(menu => {
//         if (menu.childList) {
//             menusToRoutes(menu.childList)
//         }
//         if (!menu.component) {
//             return
//         }
//         console.log(JSON.stringify(menu))
//         let route = {
//             path: menu.path,
//             component: () => import('@/components/' + menu.component),
//             meta: {
//                 requireAuth: true
//             }
//         }
//         router.addRoute('home', route)
//         // routes.push(route)
//     })
// }

router.beforeEach((to, from, next) => {
    console.log('login11111111111111')
    console.log('to = ' + to.path + ' from = ' + from.path)
    console.log('router---------------' + JSON.stringify(router.options.routes))
    console.log('router---------------' + router.getRoutes().length)

    if (store.getters.token) {
        if(to.path.startsWith('/home')){

            console.log('login222222222222222222')
            // initMenuRoutes()
            console.log('router---------------' + JSON.stringify(router.options.routes))
            console.log('router---------------' + router.getRoutes().keys());


            next()
        }else{
            console.log('login3333333333333333333')
            next()
        }

    }else{
        if (to.matched.some(r => r.meta.requiresAuth)) {
            console.log('login444444444444444444444')
            next({name: 'login'})
        }else{
            console.log('login555555555555555555')
            next()
        }
    }

    // if (store.getters.token && to.path.startsWith('/home')) {
    //     initMenuRoutes()
    // }
    // if (store.getters.token && to.path.startsWith('/login')) {
    //     next({
    //         name: 'home'
    //     })
    // }
    // // 如果前端没有登录信息则直接拦截，如果有则判断后端是否正常登录（防止构造参数绕过）
    // if (to.meta.requireAuth) {
    //     if (store.getters.token) {
    //         next()
    //     } else {
    //         next({
    //             path: 'login',
    //             query: {redirect: to.fullPath}
    //         })
    //     }
    // } else {
    //     next()
    // }




    // if (to.matched.some(r => r.meta.requiresAuth)) {
    //     if (store.getters.token) {
    //         if(to.path.startsWith('/home')){
    //             console.log('login222222222222222222')
    //             initMenuRoutes()
    //             next()
    //         }else{
    //             console.log('login3333333333333333333')
    //             next()
    //         }
    //     } else {
    //         console.log('login444444444444444444444')
    //         next({name: 'login'})
    //     }
    // } else {
    //     console.log('login555555555555555555')
    //     next()
    // }
})


Vue.use(Router)
// 解决报错
const originalPush = Router.prototype.push
const originalReplace = Router.prototype.replace
// push
Router.prototype.push = function push (location, onResolve, onReject) {
    if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch(err => err)
}
// replace
Router.prototype.replace = function push (location, onResolve, onReject) {
    if (onResolve || onReject) return originalReplace.call(this, location, onResolve, onReject)
    return originalReplace.call(this, location).catch(err => err)
}


export default router