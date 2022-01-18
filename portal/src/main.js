import Vue from 'vue'
import App from './App.vue'
import router from "./router/router.js"
import resource from 'vue-resource';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'
import './assets/style/global.css'
import axios from 'axios';

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(resource)
Vue.prototype.$axios = axios;

axios.interceptors.request.use(config=> {
  // 仅开发环境更改请求地址，不影响上线请求
  console.log(process)
  if (process && process.env && process.env.NODE_ENV === 'production') {
    config.url = config.url.replace('8080/api', '9000')
  }
  // config.url
  // config.url = config.url.replace('/api', '9000')
  console.log(config)
  return config;
}, err=> {
  return Promise.reject(err);
})
new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
