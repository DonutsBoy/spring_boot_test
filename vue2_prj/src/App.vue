<template>
  <div id="app">
    <Header />
<!--
    <div id="nav">
      <router-link to="/">홈</router-link> |
      <router-link to="/todo">할일</router-link> |
      <router-link to="/board">게시판</router-link> |
      <router-link to="/login">로그인</router-link> |
      <router-link to="/draw">추첨</router-link>
    </div>
-->    
    <div style="margin-top:10px;">
      <router-view />
    </div>
  </div>
</template>
 
<script>
import Header from './components/layout/Header.vue';

export default {
  name : "App",
  components : {
    Header,
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
