<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info" fixed="top">
            <!-- <b-navbar-brand @click="isNavbarCollapseOpen = false" to="/">Vue.js 스터디</b-navbar-brand> -->
            <b-navbar-brand to="/">Vue.js 스터디</b-navbar-brand>
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <!-- <b-collapse v-model="isNavbarCollapseOpen" id="nav-collapse" is-nav> -->
            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item to="/auto/dayprofit" :active='$route.name =="DayProfit"'>일별수익</b-nav-item>
                    <!-- <b-nav-item href="#" to="/upbit/api">업비트API</b-nav-item> -->
                    <b-nav-item to="/todo" :active='$route.name =="Todo"'>할일</b-nav-item>
                    <b-nav-item to="/todo2" :active='$route.name =="Todo2"'>할일2</b-nav-item>
                    <b-nav-item to="/board" :active='$route.name =="Board"'>게시판</b-nav-item>
                    <b-nav-item to="/upbit" :active='$route.name =="Upbit"'>업비트</b-nav-item>
                    <b-nav-item href="http://13.209.17.226:81" target="_blank">뷰티파이</b-nav-item>
                    <!-- <b-nav-item href="#" to="/login">로그인</b-nav-item> -->
                    <!-- <b-nav-item href="#" to="/draw">추첨</b-nav-item> -->
                    <!--<b-nav-item href="#" disabled>Disabled</b-nav-item>-->
                </b-navbar-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-form>
                    <b-form-input size="sm" class="mr-sm-2" placeholder="검색어 입력"></b-form-input>
                    <b-button size="sm" class="my-2 my-sm-0" @click="clicka">Search</b-button>
                    </b-nav-form>
                </b-navbar-nav>                
            </b-collapse>
        </b-navbar>
<!--         <div v-if="isNavbarCollapseOpen" @click="isNavbarCollapseOpen = false" class="navbar-backdrop">
        </div> -->
    </div>
</template>

<script>
export default {
    //name: "header",
    data() {
        return {
            //isNavbarCollapseOpen: false,
        };
    },
    watch: {
        //라우터 값이 변경되면 자동으로 실행
/*        
        '$route' () {
            //0.3초후에 뷰메소드 실행됨
            //0.1초로하면 모바일크롬에서는 잘되는데 모바일사파리에서 안된다. 그래서 0.3초로 설정했음
            setTimeout(()=>{
                this.isNavbarCollapseOpen = false;
                window.scrollTo(0,0);//스크롤이 생기는 페이지에서 로그인 페이지로 라우터 이동하면 세로 스크롤 상태로 이동되어서 최상단으로 이동후에 이동한다. 2021-05-25
            }, 300);
        }
*/        
    },
    methods: {
        clicka() {
            //window.scrollTo(0,0);
        }
    },    
};
</script>

<style>
    .navbar-backdrop {
        z-index: 1029;
        background-color: rgba(0, 0, 0, 0.5);
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
    }
</style>