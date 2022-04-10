import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/Layout";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/index',
    children:[
      {
        path: 'index',
        name: 'Index',
        component: () => import("@/views/Index"),
      },
      {
        path: 'addUser',
        name: 'AddUser',
        component: () => import("@/views/AddUser"),
      },
      {
        path: 'manageUsers',
        name: 'ManageUsers',
        component: () => import("@/views/ManageUsers"),
      },
      {
        path: 'account',
        name: 'Account',
        component: () => import("@/views/Account"),
      },
      {
        path: 'addManager',
        name: 'AddManager',
        component: () => import("@/views/AddManager"),
      },
      {
        path: 'manageManager',
        name: 'ManageManager',
        component: () => import("@/views/ManageManager"),
      },
      {
        path: 'manageAccess',
        name: 'ManageAccess',
        component: () => import("@/views/manageAccess"),
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
