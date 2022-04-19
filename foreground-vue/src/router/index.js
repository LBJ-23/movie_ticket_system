import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/Layout/Layout";



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

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
