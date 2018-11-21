<template>
    <el-scrollbar style="height: 100%;margin-bottom: -17px;" class="left-nav">
        <el-menu
                :default-active="defaultActive"
                class="el-menu-vertical-demo"
                @select="select"
                background-color="#188ae2"
                text-color="#ffffff"
                active-text-color="#ffffff"
                :collapse="collapse"
        >
            <nav-item :navMap="navMap"></nav-item>
        </el-menu>
    </el-scrollbar>
</template>

<script>
    import navItem from './nav-item'
    import navArr from '../../../router/router'

    export default {
        components: {
            navItem
        },
        props: ['collapse'],
        data() {
            return {
                navMap: [],
                defaultActive: ''
            }
        },
        created() {
            navArr.forEach(nav => {
                if (nav.meta && nav.meta.main) {
                    this.navMap = nav.children
                }
            })
            this.defaultActive = this.$route.name
        },
        watch: {
            $route() {
                this.defaultActive = this.$route.name
            }
        },
        methods: {
            select(routeName) {
                this.$router.push({name: routeName})
            },
        }
    }
</script>

<style lang='scss' type="text/scss" rel='stylesheet/scss'>
    .left-nav {
        background: #188ae2;
        height: 100%;
        overflow: hidden;
        .el-menu-vertical-demo:not(.el-menu--collapse) {
            border-right: none;
            width: 240px;
        }
        .el-scrollbar__wrap {
            height: 102%;
        }
        .el-menu {
            border-right: none;
            .el-submenu__icon-arrow {
                color: #ffffff;
            }
            .el-menu-item.is-active {
                background: #1c6fb3 !important;
            }
        }
        .el-menu--collapse {
            .svg-icon{
                font-size: 22px;
            }
            .el-submenu__icon-arrow {
                display: none;
            }
            .el-submenu__title {
                span {
                    display: none;
                }
            }
        }
    }
</style>
