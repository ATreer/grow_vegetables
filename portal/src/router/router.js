import Vue from "vue";
import VueRouter from "vue-router";

// 引入组件
import main from "../components/MainView";

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

const routes = [
    {
        path:"/",
        component: main
    },
    {
        path: "/about",
        component: about
    }
]

var router =  new VueRouter({
    routes
})
export default router;