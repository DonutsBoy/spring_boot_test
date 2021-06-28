<template>
  <div class="container">
    <div class="text-center">
      게시판
      <!-- <b-button variant="primary" @click="logoutBtn">로그아웃</b-button> -->
      sdfdsfdsfdsf<br>
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
      sdfdsfdsfdsf<br>      
    </div>
  </div>
</template>

<script>
//import axios from 'axios';

export default {
	mounted() {    
		this.$nextTick(function () {
      // 모든 화면이 렌더링된 후 실행합니다.      
      // if (sessionStorage.getItem("id") == null) {
      //   sessionStorage.setItem("refer", window.location.pathname);
      //   this.$router.push('/login');
      // }
		})
	},  
  data() {
    return {
      todoList: ['11','11'],
    } 
  },
  methods: {
/*     logoutBtn() {
      this.$store.commit('logoutSession');
    } */
  },
}
</script>

<style>
  .board {
    text-align: center;
  }
</style>