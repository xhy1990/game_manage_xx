import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueResource from 'vue-resource'
import './plugins/element.js'
import store from "@/store"
import axios from 'axios'

Vue.use(VueResource)

Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
    render: h => h(App),
    router,
    store,
}).$mount('#app')
