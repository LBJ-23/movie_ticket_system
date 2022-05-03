import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/Layout/Layout";
import Login from "@/views/login"
import MovieHeader from "@/components/MovieHeader";



const routes = [
  {
    path: '/',
    name: 'home',
    component: Layout,
    redirect: '/index',
    children:[
      {
        path: 'index',
        name: 'index',
        component: () => import("@/views/index")
      },
      {
        path: 'movie',
        name: 'movie',
        component: MovieHeader,
        redirect:'movie/nowMovie',
        children:[
          {
            path: '/nowMovie',
            name: 'nowMovie',
            component: () => import('@/views/nowMovie')
          },
          {
            path: '/soonMovie',
            name: 'soonMovie',
            component: () => import('@/views/soonMovie')
          }
        ]
      },
      {
        path: 'movieDetails',
        name: 'movieDetails',
        component: () => import('@/views/movieDetails')
      },
      {
        path: 'cinema',
        name: 'cinema',
        component: () => import('@/views/cinema')
      },
      {
        path: 'cinemaDetails',
        name: 'cinamaDetails',
        component: () => import('@/views/cinemaDetails')
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
