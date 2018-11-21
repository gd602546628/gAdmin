import main from '../views/main/index'
import routeView from '../views/main/routeView'

export default  [
    {
        path: '/',
        name: 'main',
        component: main,
        meta: {
            main: true
        },
        children: [
            {
                path: 'topNav1',
                name: 'topNav1',
                redirect:'/topNav1/sub1/test1',
                component:routeView,
                meta: {
                    title: '顶部导航1',
                    icon:'share'
                },
                children: [
                    {
                        path: 'sub1',
                        name: 'sub1',
                        component:routeView,
                        meta: {
                            title: '侧栏导航一'
                        },
                        children: [
                            {
                                path: 'test1',
                                name: 'test1',
                                component: () => import('../views/topNav1/sub1/test1'),
                                meta: {
                                    title: '侧栏一项一'
                                }
                            },
                            {
                                path: 'test2',
                                name: 'test2',
                                component: () => import('../views/topNav1/sub1/test2'),
                                meta: {
                                    title: '侧栏一项二',
                                }
                            }
                        ]
                    }
                ]
            },
            {
                path: 'topNav2',
                name: 'topNav2',
                redirect:'/topNav2/sub1/test1',
                component:routeView,
                meta: {
                    title: '顶部导航2',
                    icon:'share'
                },
                children: [
                    {
                        path: 'sub1',
                        name: 'sub11',
                        component:routeView,
                        meta: {
                            title: '侧栏导航一'
                        },
                        children: [
                            {
                                path: 'test1',
                                name: 'test11',
                                component: () => import('../views/topNav2/sub1/test1'),
                                meta: {
                                    title: '侧栏一项一'
                                }
                            },
                            {
                                path: 'test2',
                                name: 'test22',
                                component: () => import('../views/topNav2/sub1/test2'),
                                meta: {
                                    title: '侧栏一项二'
                                }
                            }
                        ]
                    }
                ]
            },
        ]
    }
]
