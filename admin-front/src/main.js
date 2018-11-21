import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/store'
import {useMock} from './config/index'
import Element from 'element-ui'
import './assets/css/element-variables.scss'
import filter from './filter/index'
import directive from './directive/index'
import customComponent from './components/index'
import mock from './mock/index'

/*svg处理*/

const requireAll = requireContext => requireContext.keys().map(requireContext);
const req = require.context('./assets/icons', true, /\.svg$/);
requireAll(req);
/*svg处理END*/

if (useMock) mock()
Vue.use(Element)
Vue.use(filter)
Vue.use(directive)
Vue.use(customComponent)
Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
