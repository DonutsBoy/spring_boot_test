import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import DayProfit from '../views/DayProfit.vue'
import Todo from '../views/Todo.vue'
import Todo2 from '../views/Todo2.vue'
import Login from '../views/Login.vue'
import NotFound from '../views/NotFound.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home //맨상단 import 부분에 Home 과 매칭된다.
  },
  {
    path: '/auto/dayprofit',
    name: 'DayProfit',
    component: DayProfit
  },
  {
    path: '/todo',
    name: 'Todo',
    component: Todo
  },
  {
    path: '/todo2',
    name: 'Todo2',
    component: Todo2
  },
  {
    path: '/board',
    name: 'Board',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Board.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '*',
    component: NotFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
