import Vue from 'vue'
import App from './App.vue'
import store from "./store/index.js"
//import store from "./store" 는 import store from "./store/index" 랑 같은 의미이다.

import axios from 'axios';
//아래와 같이 하면 이런식으로 this.$axios.get("/api/todo/insert", { 아무 vue파일에서 호출이 가능하다. 
Vue.prototype.$axios = axios;

//뷰라우터 사용하기
import router from "./router"

//부트스트랩뷰 사용하기
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue)

//VueMoment 사용하기
import VueMoment from 'vue-moment' 
Vue.use(VueMoment)

//vue session 사용하기
// import VueSession from 'vue-session'
// var sessionOptions = {
//   persist: true
// }
// Vue.use(VueSession, sessionOptions)

Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App),
  // vuetify : new Vuetify(),		//Vuetify 사용하기  
}).$mount('#app')