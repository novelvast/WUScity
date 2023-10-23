import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import ResultPage from '../views/ResultPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/result',
      name: 'ResultPage',
      component: ResultPage
    },
  ]
})

export default router
