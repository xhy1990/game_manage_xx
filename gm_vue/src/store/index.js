import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: null,
        menus: [],
        // routes: []
    },
    getters: {
        token: (state) => {
            if (!state.token) {
                state.token = window.localStorage.getItem('token')
            }
            return state.token
        },
        menus: (state) => {
            if (!state.menus || state.menus.length <= 0) {
                state.menus = JSON.parse(window.localStorage.getItem('menus'))
            }
            return state.menus
        }
        // ,
        // routes: (state) => {
        //     if (!state.routes || state.routes.length <= 0) {
        //         state.routes = JSON.parse(window.localStorage.getItem('routes'))
        //     }
        //     return state.routes
        // }
    },
    mutations: {
        token(state, token) {
            state.token = token
            window.localStorage.setItem('token', token)
        },
        initMenu(state, menus) {
            state.menus = menus
            window.localStorage.setItem('menus', JSON.stringify(menus))
        },
        // initRoute(state, routes) {
        //     state.routes = routes
        //     window.localStorage.setItem('routes', JSON.stringify(routes))
        // },
        logout(state) {
            try {
                window.localStorage.removeItem('token')
                state.token = null
                window.localStorage.removeItem('menus')
                state.menus = []
                // window.localStorage.removeItem('routes')
                // state.routes = []
            } catch (e) {
                console.log(e)
            }
        }
    }
})
