const build = 1
const useMock = false
const baseUrlMap = {
    0: '',
    1: 'http://localhost:8100/'
}

const baseUrl = baseUrlMap[build]

export {baseUrl, useMock}