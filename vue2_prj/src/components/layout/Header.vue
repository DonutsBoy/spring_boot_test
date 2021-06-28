<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="info" fixed="top">
        	<!-- 아래와같이 @click="isNavbarCollapseOpen = false" 를 사용하면 메뉴가 2번 왔다갔다 하다가 닫힌다. 그래서 코드 삭제 -->
            <!-- <b-navbar-brand @click="isNavbarCollapseOpen = false" to="/"> -->
            <b-navbar-brand @click="brandClick" to="/" id="nav-brand">
            	<img src="/favicon.ico" class="d-inline-block align-top" alt="Kitten">
            	Vue.js 스터디 2
            </b-navbar-brand>
            <!-- <b-navbar-brand to="/">{{$store.state.data_1}}</b-navbar-brand> -->
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-collapse v-model="isNavbarCollapseOpen" id="nav-collapse" is-nav>
            <!-- <b-collapse id="nav-collapse" is-nav> -->
                <!-- <b-navbar-nav v-if="$route.name !== 'Todo'"> -->
                <b-navbar-nav>
                    <b-nav-item to="/auto/dayprofit" :active='$route.name =="DayProfit"'>일별수익</b-nav-item>
                    <!-- <b-nav-item href="#" to="/upbit/api">업비트API</b-nav-item> -->
                    <b-nav-item to="/todo" :active='$route.name =="Todo"'>할일</b-nav-item>
                    <b-nav-item to="/todo2" :active='$route.name =="Todo2"'>할일2</b-nav-item>
                    <b-nav-item to="/board" :active='$route.name =="Board"'>게시판</b-nav-item>
                    <b-nav-item to="/upbit" :active='$route.name =="Upbit"'>업비트</b-nav-item>
                    <b-nav-item href="http://13.209.17.226:81" target="_blank">뷰티파이</b-nav-item>
                    <!-- <b-nav-item href="#" to="/draw">추첨</b-nav-item> -->
                    <!--<b-nav-item href="#" disabled>Disabled</b-nav-item>-->
                </b-navbar-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-form>
                    <b-form-input size="sm" class="mr-sm-2" placeholder="검색어 입력"></b-form-input>
                    <b-button size="sm" class="my-2 my-sm-0" @click="clickSearch">Search</b-button>
                    </b-nav-form>
                    
			        <b-nav-item-dropdown text="Lang" right>
			          <b-dropdown-item href="#">EN</b-dropdown-item>
			          <b-dropdown-item href="#">ES</b-dropdown-item>
			          <b-dropdown-item href="#">RU</b-dropdown-item>
			          <b-dropdown-item href="#">FA</b-dropdown-item>
			        </b-nav-item-dropdown>
        			        
                </b-navbar-nav>                
            </b-collapse>
        </b-navbar>
        <div v-if="isNavbarCollapseOpen" @click="isNavbarCollapseOpen = false" class="navbar-backdrop">
        </div>
    </div>
</template>

<script>
export default {
    name: "Header",
    data() {
        return {
            isNavbarCollapseOpen: false,
        };
    },
	mounted() {    
		// 모든 화면이 렌더링된 후 실행합니다.
		this.$nextTick(function () {
			console.log("menu mounted");
		})
	},    
    watch: {    	
        //라우터 값이 변경되면 자동으로 실행 
        //'$route' () {
        '$route' (to, from) {
            
            sessionStorage.setItem("homeMenuRunYn", "N");
        	
/*         	console.log("to", to);
            console.log("from", from);
 */            
        	//0.1초후에 뷰메소드 실행됨
            //0.1초로하면 아이폰 크롬에서는 메뉴가 닫히는데 아이폰 사파리에서는 안닫힌다. 그래서 0.3초로 설정했음
            //2021-06-26 : 0.3초하니까 아이폰 사파리인지 아이폰 크롬인지에서 메뉴가 안닫히는일이 발생해서 0.35초로 설정
            setTimeout(()=>{
            	//상단메뉴를 닫는다.
            	this.isNavbarCollapseOpen = false;
            }, 350);
        }
/*
        '$route' () {
    		setTimeout(()=>{
	            const element = document.querySelector("#nav-collapse");
	            let isShown = element.classList.contains("show");
	            if(isShown){
	                this.$root.$emit('bv::toggle::collapse', 'nav-collapse')
	            }
            }, 400);            
        }
*/        
    },
    methods: {
    	clickSearch() {
    		
        },
        brandClick() {
/* 			if(this.isNavbarCollapseOpen) {
				this.$root.$emit('bv::toggle::collapse', 'nav-collapse')
			} */      
			
			//메뉴이동하면서 brand를 클릭하면 실행안되고 메뉴이동안하고 그냥 brand를 누르면
			//실행되게 코드를 작성했음
			//이유는 아이폰 사파리에서는 괜찮은데 아이폰 크롬에서 메뉴이동할때 아래 brand 클릭이벤트가 실행되면
			//이쁘지않게 열렸다 닫혔다가 몇번 동작해서 미관상 보기 안좋아서 그렇다.
			if (sessionStorage.getItem("homeMenuRunYn") != "N") {
	            const element = document.querySelector("#nav-collapse");
	            console.log("homeMenuRunYn !!!!");
	            let isShown = element.classList.contains("show");
	            if(isShown){
	                this.$root.$emit('bv::toggle::collapse', 'nav-collapse');
	            }
			} else {
				sessionStorage.setItem("homeMenuRunYn", "Y");
			}
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