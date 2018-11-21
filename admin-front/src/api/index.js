import http from './http'
import api from './api'

export default {
  install(Vue) {
    Vue.prototype.$http = http
    Vue.prototype.$api = api
  }
}
