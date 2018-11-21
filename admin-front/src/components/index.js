import iconSvg from './iconSvg'

const map = {
    iconSvg
}
export default {
    install(Vue, options) {
        Object.keys(map).forEach(key => {
            Vue.component(key, map[key])
        })
    }
}
