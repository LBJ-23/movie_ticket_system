import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/Layout/Layout";
import Login from "@/views/login"



const routes = [
  {
    path: '/',
    name: 'home',
    component: Layout,
    redirect: '/index',
    children:[
      {
        path: 'index',
        name: 'Index',
        component: () => import("@/views/index.vue")
      },
      {
        path: 'movie',
        name: 'Movie',
        component: () => import("@/views/movie.vue")
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
