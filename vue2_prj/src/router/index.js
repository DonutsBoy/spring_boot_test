import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import DayProfit from '../views/DayProfit.vue'
import Todo from '../views/Todo.vue'
import Todo2 from '../views/Todo2.vue'
import Login from '../views/Login.vue'
import Login2 from '../views/Login2.vue'
import NotFound from '../views/NotFound.vue'
import axios from 'axios';
import store from '../store/index.js'

Vue.use(VueRouter)

//세션 로그인 체크
const requireAuth = () => (to, from, next) => {  
  axios.get("/api/login/session/check", {
    params: {}
  }).then((response) => {        
    //console.log("/api/login/session/check : ", response.data);
    let loginSuccess = response.data.success;
    //로그인상태
    if(loginSuccess == true) {
		//window.scrollTo(0,0);//스크롤이 생기는 페이지에서 로그인 페이지로 라우터 이동하면 세로 스크롤 상태로 이동되어서 최상단으로 이동후에 이동한다. 2021-05-25
        return next();//현재 라우터 페이지로 그대로 이동한다.
    //비로그인상태
    } else {
      //sessionStorage.clear();
      //console.log("window.location.pathname : ", window.location.pathname);
      //console.log("from.fullPath : ", from.fullPath);
      //console.log("to.fullPath : ", to.fullPath);
      sessionStorage.setItem("refer", to.fullPath);
      //window.scrollTo(0,0);//스크롤이 생기는 페이지에서 로그인 페이지로 라우터 이동하면 세로 스크롤 상태로 이동되어서 최상단으로 이동후에 이동한다. 2021-05-25
      return next('/login');
    }  
  }).catch((error) => {
    console.log(error);
    alert(error);
  });
};

//토큰 로그인 체크
const requireAuth2 = () => (to, from, next) => {
  if (store.state.token != "") {
    //console.log("token : ", store.state.token);  
    axios.get("/api/login/token/check", {
      headers: {
        //"x-access-token": store.state.token
      },
      params: { token: store.state.token }
    }).then((response) => {        
      //console.log("/api/login/token/check : ", response.data);
      let loginSuccess = response.data.success;
      //로그인상태
      if(loginSuccess == true) {
          window.scrollTo(0,0);//스크롤이 생기는 페이지에서 로그인 페이지로 라우터 이동하면 세로 스크롤 상태로 이동되어서 최상단으로 이동후에 이동한다. 2021-05-25
          return next();//현재 라우터 페이지로 그대로 이동한다.
      //비로그인상태
      } else if(loginSuccess == false) {
        store.commit('logoutToken'); //스토어에 token 삭제       
        //sessionStorage.clear();
        //console.log("window.location.pathname", window.location.pathname);
        //console.log("from.fullPath : ", from.fullPath);
        //console.log("to.fullPath : ", to.fullPath);
        sessionStorage.setItem("refer", to.fullPath);
        //window.scrollTo(0,0);//스크롤이 생기는 페이지에서 로그인 페이지로 라우터 이동하면 세로 스크롤 상태로 이동되어서 최상단으로 이동후에 이동한다. 2021-05-25
        return next('/login2');
      }  
    }).catch((error) => {
      console.log(error);
      alert(error);
    });
  //token 값이 없어서 로그아웃상태라고 판단
  } else {
    sessionStorage.setItem("refer", to.fullPath);
    //window.scrollTo(0,0);//스크롤이 생기는 페이지에서 로그인 페이지로 라우터 이동하면 세로 스크롤 상태로 이동되어서 최상단으로 이동후에 이동한다. 2021-05-25
    return next('/login2');
  }
};

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home, //맨상단 import 부분에 Home 과 매칭된다.
    meta: {title: 'Team Member'},    
  },
  {
    path: '/auto/dayprofit',
    name: 'DayProfit',
    component: DayProfit,
    beforeEnter: requireAuth2(), //토큰 로그인 체크    
  },
  {
    path: '/todo',
    name: 'Todo',
    component: Todo,
  },
  {
    path: '/todo2',
    name: 'Todo2',
    component: Todo2,
  },
  {
    path: '/board',
    name: 'Board',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Board.vue'),
  },
  {
    path: '/upbit',
    name: 'Upbit',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Upbit.vue'),

    //이 함수는 경로에 따라 라우트 매칭을 결정하고 해당 컴포넌트를 생성하기 직전에 호출되는 함수다.
    //여기서 인증 여부를 판단한 뒤 컴포넌틀 랜더링을 진행하거나 혹은 로그인 페이지로 이동하는 등의 로직을 구현하면 된다.   
    //라우터 이동 전 beforeEnter 가 먼저 실행됨.  
    beforeEnter: requireAuth(), //세션 로그인 체크    
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/login2',
    name: 'Login2',
    component: Login2,
  },
  {
    path: '*',
    component: NotFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  //스크롤을 내린 상태에서 라우터를 통해 다른 페이지로 이동할 경우, 스크롤이 초기화되지 않고 내려가있는 상태로 옮겨간다
  //아래 scrollBehavior 함수가 스크롤 이슈를 해결해준다.
  //scrollBehavior (to, from, savedPosition) {
  scrollBehavior () {
      return { x: 0, y: 0 }
  },
})

// router.afterEach((to,from) => { 
//   window.scrollTo(0,0);//스크롤이 생기는 페이지에서 로그인 페이지로 라우터 이동하면 세로 스크롤 상태로 이동되어서 최상단으로 이동후에 이동한다. 2021-05-25
//   console.log(from.path + ' zzzz-> ' + to.path);
// })

export default router
