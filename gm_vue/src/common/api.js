//
// const initMenu = (router, store) => {
//     // 防止重复触发加载菜单操作
//     if (store.getters.menus.length > 0) {
//         return
//     }
//     this.$http.get('/gm/menu').then(resp => {
//         if (resp && resp.status === 200 && resp.data.success) {
//             var fmtRoutes = formatRoutes(resp.data.data)
//             router.addRoutes(fmtRoutes)
//             store.commit('initMenu', resp.data.data)
//         }
//     })
// }
//
// const formatRoutes = (menus) => {
//     let fmtRoutes = []
//     menus.forEach(menu => {
//         if (menu.childList) {
//             menu.childList = formatRoutes(menu.childList)
//         }
//
//         let fmtRoute = {
//             path: menu.path,
//             component: resolve => {
//                 require(['@/components/' + menu.component + '.vue'], resolve)
//             },
//             name: menu.routeName,
//             title: menu.name,
//             meta: {
//                 requireAuth: true
//             },
//             children: menu.children
//         }
//         fmtRoutes.push(fmtRoute)
//     })
//     return fmtRoutes
// }
//
// let api = {
//     initMenu(router, store) {
//         initMenu(router, store)
//     }
//
// }
// export default api
