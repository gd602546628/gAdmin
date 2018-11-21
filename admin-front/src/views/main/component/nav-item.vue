<template>
    <div>
        <template v-for="nav in navMap">
            <el-submenu v-if="nav.children&&nav.children.length>0" :index="nav.name">
               <template slot="title">
                   <icon-svg v-if="nav.meta.icon" :icon-class="nav.meta.icon" style="margin-right: 10px;"></icon-svg>
                   <span slot="title">{{nav.meta.title}}</span>
               </template>
                <nav-item :navMap="nav.children"></nav-item>
            </el-submenu>
            <el-menu-item v-else :index="nav.name">
                <icon-svg v-if="nav.meta.icon" :icon-class="nav.meta.icon" style="margin-right: 10px;"></icon-svg>
                <span slot="title">{{nav.meta.title}}</span>
            </el-menu-item>
        </template>
    </div>
</template>

<script>


    export default {
        name: 'nav-item',
        components: {},
        props: ['navMap'],
        data() {
            return {}
        },
        created() {

        },
        methods: {}
    }
</script>

<style lang='scss' type="text/scss" rel='stylesheet/scss' scoped>

</style>
