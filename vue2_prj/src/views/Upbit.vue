<template>
  <div class="container">
    <b-card-group deck>
      <b-card
        header="코인 계좌 조회"
        title="원화"
      >
        <b-card-text>{{ krw_amt }}</b-card-text>
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

    <div class="mt-3">
      <b-card-group deck>
        <b-card bg-variant="primary" text-variant="white" header="Primary" class="text-center">
          <b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
        </b-card>

        <b-card bg-variant="secondary" text-variant="white" header="Secondary" class="text-center">
          <b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
        </b-card>

        <b-card bg-variant="success" text-variant="white" header="Success" class="text-center">
          <b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
        </b-card>
      </b-card-group>
    </div>

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
      krw_amt: 0,
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

      axios.get("/api/upbit/account/select", {
        params: {}
      }).then((response) => {
        console.log(response.data);
        this.krw_amt = String(parseInt(response.data[0].balance)).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' 원';
        //this.krw_amt = String(response.data[0].balance).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";//천단위 콤마표시
        // this.show = false;
      }).catch((error) => {
        console.log("error", error);
        alert(error);
      });
      
    },
    doLogout() {
      
      //this.$store.commit('logoutToken');
      this.$store.commit('logoutSession');      

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