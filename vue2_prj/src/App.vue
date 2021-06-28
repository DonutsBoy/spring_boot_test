<template>
  <div id="app">
    <!-- 헤더 컴포넌트 사용 -->    
    <Header />
    <!-- 상단메뉴 고정일때 사용 -->
    <div style="height:56px"></div>
<!--
    <div id="nav">
      <router-link to="/">홈</router-link> |
      <router-link to="/todo">할일</router-link> |
      <router-link to="/board">게시판</router-link> |
      <router-link to="/login">로그인</router-link> |
      <router-link to="/draw">추첨</router-link>
    </div>
-->    
    <div style="margin-top:10px;margin-bottom:10px;">
      <router-view></router-view>
    </div>
        
    <!-- 푸터 컴포넌트 사용 -->    
    <Footer />
  </div>
</template>
 
<script>
import Header from './components/layout/Header.vue';
import Footer from './components/layout/Footer.vue';

export default {
  name : "App",
  components : {
    Header,
    Footer,
  }
}
</script>

<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#nav {
  padding: 10px;
  text-align: center;
  background: #eeeeee;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

</style>