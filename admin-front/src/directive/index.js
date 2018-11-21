let test = {
  inserted: function (el) {
    console.log(el)
  }
}

let map = {
  test: test
}
export default {
  install(Vue) {
    Object.keys(map).forEach(key => {
      Vue.directive(key, map[key])
    })
  }
}
