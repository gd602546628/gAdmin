const map = {
}

export default {
  install(Vue) {
    Object.keys(map).forEach(key => {
      Vue.filter(key, map[key])
    })
  }
}
