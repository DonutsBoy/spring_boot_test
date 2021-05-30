<template>
  <div class="container">
<!--     <b-form @submit="onSubmit" @reset="onReset" v-if="show"> -->    
    <b-card class="mt-3" header="JWT 토큰 로그인">
      <b-form-group id="input-group-2" label="아이디" label-for="input-1">
        <b-form-input
          type="text"
          id="input-1"
          v-model="form.id2"
          placeholder="아이디 입력"
          @keyup.enter="loginToken"
        ></b-form-input>
      </b-form-group>
      <!-- required -->
      <b-form-group id="input-group-2" label="패스워드" label-for="input-2">
        <b-form-input
          type="password"
          id="input-2"
          v-model="form.pw2"
          placeholder="패스워드 입력"
        ></b-form-input>
      </b-form-group>
      <b-button variant="primary" @click="loginToken">로그인</b-button>
      &nbsp;<span v-html="loginRtnMsg2"></span>
      <b-overlay :show="show2" variant="info" opacity="0.40" no-wrap rounded="sm">
      </b-overlay>        
    </b-card>
    
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card>
    <!-- {{ posts }} -->
     
  </div>
</template>

<script>
  export default {
    mounted() {    
      // 모든 화면이 렌더링된 후 실행합니다.
      this.$nextTick(function () {
        //this.$store.commit('loginSessionSuccessRedirect', '/');
      })
    },    
    data() {
      return {
        show2: false,
        loginRtnMsg2: "",
        form: {
          id2: 'admin',
          pw2: '',
        },
        // show: true,
        posts: [],
      }
    },
    methods: {
      loginToken() {
        this.loginRtnMsg2 = "";
        this.show2 = true;
        this.$axios.get("/api/login/token", {
          params: { id: this.form.id2, pw: this.form.pw2 }
        }).then((response) => {        
          console.log("response.data", response.data);
          //로그인성공
          if (response.data.success == true) {
            sessionStorage.setItem("token", response.data.token); //세션스토리지 저장
            this.$store.commit('loginToken', response.data.token); //뷰스토어 저장            
            let redirect = sessionStorage.getItem("refer");
            if (redirect == null) {
              redirect = "/";
            }
            console.log("redirect : ", redirect);
            this.$router.push(redirect); //로그인 성공시 리다이렉트 페이지로 이동
          //로그인실패
          } else {
            // this.show = false;
             this.loginRtnMsg2 = "<font color=red>로그인 실패</font>";
          }
          this.show2 = false;
        }).catch((error) => {
          console.log(error);
          alert(error);
        });
      }
    }
  }
</script>