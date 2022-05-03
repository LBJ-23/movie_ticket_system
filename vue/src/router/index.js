import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/Layout";
import login from "@/views/login";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/index',
    children:[
      {
        path: 'index',
        name: 'index',
        component: () => import("@/views/Index"),
      },
      {
        path: 'addUser',
        name: 'addUser',
        component: () => import("@/views/AddUser"),
      },
      {
        path: 'manageUsers',
        name: 'manageUsers',
        component: () => import("@/views/ManageUsers"),
      },
      {
        path: 'account',
        name: 'account',
        component: () => import("@/views/Account"),
      },
      {
        path: 'addManager',
        name: 'addManager',
        component: () => import("@/views/AddManager"),
      },
      {
        path: 'manageManager',
        name: 'manageManager',
        component: () => import("@/views/ManageManager"),
      },
      {
        path: 'manageAccess',
        name: 'manageAccess',
        component: () => import("@/views/ManageAccess"),
      },
      {
        path: 'addMovie',
        name: 'addMovie',
        component: () => import("@/views/AddMovie")
      },
      {
        path: 'manageMovie',
        name: 'manageMovie',
        component: () => import("@/views/ManageMovie")
      },
      {
        path: 'addCinema',
        name: 'addCinema',
        component: () => import("@/views/AddCinema")
      },
      {
        path: 'manageCinema',
        name: 'manageCinema',
        component: () => import("@/views/ManageCinema")
      },
      {
        path: 'addSession',
        name: 'addSession',
        component: () => import("@/views/AddSession")
      },
      {
        path: 'manageSession',
        name: 'manageSession',
        component: () => import("@/views/ManageSession")
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: login,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
