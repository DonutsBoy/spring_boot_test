<template>
  <div class="container">
<!--     <b-form @submit="onSubmit" @reset="onReset" v-if="show"> -->
    <b-card title="세션 기반 로그인" aria-hidden="true">
      <b-form @submit="onSubmit">
        <b-form-group id="input-group-2" label="아이디" label-for="input-1">
          <b-form-input
            type="text"
            id="input-1"
            v-model="form.id"
            placeholder="아이디 입력"
          ></b-form-input>
        </b-form-group>
        <!-- required -->
        <b-form-group id="input-group-2" label="패스워드" label-for="input-2">
          <b-form-input
            type="password"
            id="input-2"
            v-model="form.pw"
            placeholder="패스워드 입력"
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary">로그인</b-button>
        &nbsp;<span v-html="loginRtnMsg"></span>    
      </b-form>
      <b-overlay :show="show" variant="secondary" opacity="0.40" no-wrap rounded="sm">
      </b-overlay>        
    </b-card>
    <!-- <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card>
    {{ posts }} -->
  </div>
</template>

<script>
  export default {
    mounted() {    
      // 모든 화면이 렌더링된 후 실행합니다.
      this.$nextTick(function () {
        this.$store.commit('loginSessionSuccessRedirect', '/');
      })
    },    
    data() {
      return {
        show: false,
        loginRtnMsg: "",
        show2: false,
        loginRtnMsg2: "",
        form: {
          id: 'admin',
          pw: '',
        },
        // show: true,
        posts: [],
      }
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        this.loginRtnMsg = '';
        this.show = true;
        this.$axios.get("/api/login/session", {
          params: {id: this.form.id, pw: this.form.pw}
        }).then((response) => {        
          console.log("/api/login/session", response.data);
          if (response.data.success == true) {
            this.show = false;
            sessionStorage.setItem("id", response.data.id); //세션스토리지 저장
            this.$store.commit('loginId', response.data.id); //뷰스토어 저장            
            let redirect = sessionStorage.getItem("refer");
            if (redirect == null) {
              redirect = "/";
            }
            this.$router.push(redirect); //로그인 성공시 리다이렉트 페이지로 이동
          //로그인실패
          } else {
            this.show = false;
            this.loginRtnMsg = "<font color=red>로그인 실패</font>";
          }
        }).catch((error) => {
          console.log(error);
          alert(error);
        });
      },
    }
  }
</script>