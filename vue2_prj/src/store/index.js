import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
import router from "../router"

//vuex의 store값을 새로고침해도 사라지지 않게 유지시켜주는 플러그인 사용하기
import createPersistedState from 'vuex-persistedstate';

//상태 관리를 위해 사용하는 Vuex
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //데이터가 들어가는곳
    id: "",
    count: 0,
    host: "http://127.0.0.1",
    token: "",    
  },
  //Vuex 의 데이터, 즉 state 값을 동기적 변경하는 로직들을 의미 
  mutations: {
    increment (state) {
      state.count++
    },    
    loginToken(state, payload) {
      state.token = payload;
    },
    logoutToken(state) {
      state.token = "";
      //router.push("/upbit");
      //위와 같이 하면 같은 페이지로 라우터 이동 명령을 주면 아래 오류가 난다. 
      //NavigationDuplicated: Avoided redundant navigation to current location
      //현재 페이지에서 다시 현재 페이지를 라우터로 이동하면 위의 오류가 난다.

      //그래서 현재 페이지를 새로고침하는 아래 명령을 준다. 그러면 오류가 안난다.
      //근데 단점이 뭔가 페이지 이동되는 시간이 오래 걸린다.
      //router.go(router.currentRoute);

      router.push("/login2");
    },
    loginId (state, payload) {
      state.id = payload;
    },    
    logoutId (state) {
      state.id = "";
    },    
    logoutSession (state) {
      console.log("state",state);
      axios.get("/api/logout/session", {
        headers: {},
        params: {}
      }).then((response) => {        
        console.log("/api/logout/session : ", response.data);
        state.id = "";
        router.push("/"); //메인페이지로 이동
      }).catch((error) => {
        console.log(error);
        alert(error);
      });
    },
    loginSessionSuccessRedirect (redirect) {
      axios.get("/api/login/session/check", {
        headers: {},
        params: {}
      }).then((response) => {        
        console.log("/api/login/session/check", response.data.success);
        if (response.data.success == true) {
          console.log(response.data.success);
          console.log("redirect", redirect);
          router.push(redirect);          
        } else {
          console.log(response.data.success);
        }
      }).catch((error) => {
        console.log(error);
        alert(error);
      });
    },
  },
  //Vuex 의 데이터, 즉 state 값을 비동기적 변경하는 로직들을 의미 
  actions: {
    //
  },
  modules: {
    //
  },
  //vuex의 getters를 사용하는 이유는 자주 처리해야 하는 state의 데이터를 캐시를 통해 코드의 반복을 줄여주기 때문입니다. 
  getters: {
    getId: function (state) {
      return state.id + '_hello';
    }    
  },
  //vuex의 store값을 새로고침해도 사라지지 않게 유지시켜주는 플러그인 사용하기
  plugins: [
    createPersistedState()
  ],  
});