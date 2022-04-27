import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import '@/assets/css/global.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

createApp(App).use(store).use(router).use(ElementPlus,{locale: zhCn}).mount('#app')

//取出session里缓存的用户信息
// const loginUser = sessionStorage.getItem("loginUser")
// console.log(loginUser)
// if (!loginUser){
//     router.replace("login")
// }