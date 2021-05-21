import Vue from 'vue'
import App from './App.vue'
import store from "./store"

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

//Vuetify 사용하기
// import Vuetify from 'vuetify'
// import 'vuetify/dist/vuetify.min.css'
// Vue.use(Vuetify);

Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App),
  // vuetify : new Vuetify(),		//Vuetify 사용하기  
}).$mount('#app')