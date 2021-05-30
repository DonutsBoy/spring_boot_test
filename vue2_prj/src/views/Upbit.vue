<template>
  <div class="container">
    <b-card-group deck>
      <b-card
        header="로그인상태"
        title="타이틀"
      >
        <b-card-text>XXXXX</b-card-text>
        <b-button href="#" variant="primary" @click="doLogout">로그아웃</b-button>
      </b-card>

      <b-card 
        :title=title 
        header-tag="header" 
      >
        <template #header>
          <h6 class="mb-0">코인 현재가 조회</h6>
        </template>
        <b-card-text>{{ price }}</b-card-text>
        <b-button href="#" variant="primary" v-on:click="selectCoinPrice">조회</b-button>
        <b-overlay :show="show" :variant="variant" :opacity="opacity" no-wrap>
        </b-overlay>        
      </b-card>
    </b-card-group>    
  </div>

</template>

<script>
import axios from 'axios';

export default {
	mounted() {    
		this.$nextTick(function () {

      //5초 간격으로  뷰메소드 실행됨
      // setInterval(() => {
      //     this.selectCoinPrice();
      // }, 5000);

      this.selectCoinPrice();

		})
	},  
  data() {
    return {
      show: false,
      variant: 'secondary',
      opacity: 0.40,
      title: "코인명",
      price: 0,
    } 
  },
  methods: {
    selectCoinPrice() {
      this.show = true;
      axios.get("/api/upbit/coin/info", {
        params: {}
      }).then((response) => {
        console.log(response.data);
        this.title = response.data[0].market;
        this.price = String(response.data[0].trade_price).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";//천단위 콤마표시
        this.show = false;
      }).catch((error) => {
        console.log("error", error);
        alert(error);
      });
    },
    doLogout() {
      
      this.$store.commit('logoutToken');

      // axios.get("/api/logout", {
      //   params: {}
      // }).then((response) => {
      //   console.log(response.data);
      //   console.log(sessionStorage.length); //세션스토리지 저장된 데이터갯수 출력
      //   //sessionStorage.removeItem("mineSession"); // 세션스토리지 데이터 삭제
      //   sessionStorage.clear(); //세션스토리지 모든데이터 삭제
      //   this.$store.commit('logoutId'); //뷰스토어 초기화
      //   this.$router.push('/');
      // }).catch((error) => {
      //   console.log("error", error);
      //   alert(error);
      // });
    }
  },

}
</script>
<style>
</style>