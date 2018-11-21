import axios from 'axios'
import adapter from '../adapter/index'
import {Code} from '../config/index'
import {MessageBox, Message} from 'element-ui'
import store from '../store/index'
import router from '../router/index'

/*请求拦截*/
axios.interceptors.request.use((config) => {
  let userInfo = store.getters.userInfo
  if (userInfo && userInfo.token) {
    config.headers['token'] = userInfo.token
  }
  return config
})

/*响应拦截*/
axios.interceptors.response.use((response) => {
  const data = response.data
  switch (data.code) {
    case Code.DISABLE_TOKEN:
      Message({message: '登录已失效，请重新登录', type: 'error'})
      store.dispatch({type: 'logoutAction'})
      router.push({name: 'login'})
      throw new Error('登录已失效')
    case Code.NO_AUTHORITY:
      Message({message: '对不起，你没有该操作的权限', type: 'error'})
      throw new Error('没有权限')
  }
  return data
}, (err) => { // 状态码不为200
  Message({message: '网络异常', type: 'error'})
  Promise.reject(err)
})
export default {
  async post(url, params) {
    let data = await axios.post(url, params)
    if (adapter[url]) {
      data = adapter[url](data)
    }
    return data
  },
  async formPost(url, params) { //form类型Post请求
    let formData = new FormData()
    for (let key in params) {
      formData.append(key, params[key])
    }
    let data = await axios({
      url: url,
      method: 'post',
      data: formData,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
        /* 'Content-Type': 'multipart/form-data' */
      }
    })
    return data
  }
}
