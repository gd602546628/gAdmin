<template>
    <div class="main">
        <div class="header">
            <div class="title">
                <p class="text">XX管理系统</p>
                <icon-svg icon-class="menu" style="color: #000000" class="icon"
                          @click.native="switchCollapse"></icon-svg>
            </div>
        </div>
        <div class="content-box">
            <nav-component :collapse="collapse"></nav-component>
            <div class="content">
                <route-view></route-view>
            </div>
        </div>
    </div>
</template>

<script>
    import navComponent from './component/nav'
    import RouteView from "./routeView";

    export default {
        components: {
            RouteView,
            navComponent
        },
        data() {
            return {
                collapse: false
            }
        },
        created() {

        },
        methods: {
            switchCollapse() {
                this.collapse = !this.collapse
            }
        }
    }
</script>

<style lang='scss' type="text/scss" rel='stylesheet/scss' scoped>
    .main {
        height: 100%;
        display: flex;
        flex-direction: column;
        .header {
            height: 60px;
            background: #fff;
            border-bottom: 1px solid #e0e4e9;
            box-shadow: 10px 1px 15px rgba(0, 0, 0, .17);
            .title {
                width: 240px;
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100%;
                .text {
                    flex: 1;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }
                .icon {
                    cursor: pointer;
                }
            }
        }
        .content-box {
            flex: 1;
            display: flex;
            overflow: hidden;
            .content {
                flex: 1;
            }
        }
    }
</style>
