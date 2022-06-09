import Vue from 'vue'
import './plugins/axios'
// import axios from 'axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'

import BootstrapVue from 'bootstrap-vue'

Vue.use(BootstrapVue)

import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap/dist/css/bootstrap.mini.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

// 全局挂载axios
// Vue.prototype.$axios = axios

new Vue({
  beforeCreate() {
    Vue.prototype.$bus = this //安装全局事件总线，$bus就是当前应用的vm
  },
  // axios,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
