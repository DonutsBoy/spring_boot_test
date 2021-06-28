<template>
	<!-- <div class="container"> -->
	<b-container>
		<div class="text-center mb-2">
			<img alt="Vue logo" width="20%" src="../assets/logo.png">
			<HelloWorld msg="Vue.js는 가볍고 빠르다.4" />
			<div style="height:5px;"></div>
      		<b-button @click="excelDown1">엑셀다운1</b-button>
      		&nbsp;		
      		<b-button @click="excelDown2">엑셀다운2</b-button>		
      		&nbsp;		
      		<b-button @click="buttonClick">jQuery</b-button>		
      		<div id="div1">제이쿼리 사용하기</div>
      		
		  <div id="computed">
		    <hr>
		    <input type="text" v-model="message" /><br>
		  	<b>Computed 예제</b><br>
		    원본 메시지: "{{ message }}"<br>
		    역순으로 표시한 메시지: "{{ reversedMessage }}"<br>
		  	<b>watch 예제</b><br>
		    <p>역순으로 표시한 watch 메시지: "{{ messageWatch }}"</p>
		    <p>역순으로 표시한 method 메시지: "{{ getMessage() }}"</p>
		    <hr>
		  </div>
      		
		</div>
		<b-row class="text-center mb-2">
			<b-col cols="6" style="background-color:#00ff00;">12그리드시스템=6</b-col>
			<b-col cols="6" style="background-color:#ff0000;">12그리드시스템=6</b-col>
		</b-row>		
		<b-row class="text-center">
			<!-- <JobCard v-for="job in jobs" :key="job.id" :name="job.name" /> -->
			
			<!-- index는 0부터 채번된다. -->
			<JobCard v-for="(data, index) in jobs" :key="index" :name="data.name" />
		</b-row>
		<form ref="form1" action="/api/excel/download"></form>
	</b-container>
	<!-- </div> -->	
</template>
<script>
import $ from 'jquery';
import HelloWorld from '@/components/HelloWorld.vue';
import JobCard from '@/components/JobCard.vue';
export default {
	created() {
		console.log("home created");		
	},
	mounted() {    
		// 모든 화면이 렌더링된 후 실행합니다.
		this.$nextTick(function () {
			console.log("home mounted");
            //sessionStorage.setItem("homeStateYn", "Y");

			// this.$store.state.count++;
			// console.log("2 : ", this.$store.state.count);
			// this.$store.commit('increment');
			//console.log("3 : ", this.$store.state.count);
			//console.log("count : ", this.$store.state.count);
			//console.log("id : ", this.$store.state.id);
			//console.log("getId", this.$store.getters.getId);			
			this.fetchData();
		}) 
	},
	beforeDestroy() {
		console.log("home beforeDestroy");
        //sessionStorage.setItem("homeStateYn", "N");
	},
	destroyed() {
		console.log("home destroyed");		
	},
	data() {
		return {
			jobs: [],
		    message: '안녕하세요',
		    messageWatch: '',
		}
	},
	components: {
		HelloWorld,
		JobCard,
	},
    computed: {
    	//reversedMessage : computed(컴퓨티드:계산) 프로퍼티
    	//message 를 감시하고 있다가 message의 값이 변경되면 reverseMessage 를 다시 계산한다.
    	//결국, computed는 사용하기 편하고, 자동으로 값을 변경하고 캐싱해주는 
    	//아주 끝내주는 "반응형 getter"라 부를 수 있겠다. 
		reversedMessage: function () {
			console.log("11");
			return this.message.split('').reverse().join('');
		},
    },	
    //watch는 Vue 인스턴스의 특정 프로퍼티가 변경될때 지정한 콜백함수가 실행되는 기능
    watch: {
    	//message 프로퍼티가 변경될때 콜백함수가 실행된다.
    	//message 프로퍼티가 변경되면 변경된 값을 콜백함수의 첫번째 인자로 전달하고, 이전 값을 두번째 인자로 전달하여 실행한다.
		//watch는 특정 프로퍼티의 변경시점에 특정 액션(call api, push route …)을 취하고자 할때 적합하다.    	
    	message: function (newVal, oldVal) {
    		console.log(newVal);
    		console.log(oldVal);
			this.messageWatch = newVal.split('').reverse().join('');
		}
    },    
	methods: {
		async fetchData() {
			const res = await fetch("jobs.json");
			//const res = await fetch("https://jsonplaceholder.typicode.com/todos");			
			const val = await res.json();
			this.jobs = val;
			console.log(res);
		},
		excelDown1() {
			location.href = "/api/excel/download";
/* 			this.$axios.get("/api/excel/download", {
			    params: {}
			  }).then((response) => {        
			    console.log(response.data);
			  }).catch((error) => {
			    console.log(error);
			    alert(error);
			  });
 */		},
		excelDown2() {
	 		this.$refs.form1.submit()
		},
		buttonClick() {
			$("#div1").text("");
			this.message = "12345";
		},
		getMessage() {
			return this.message.split('').reverse().join('');			
		}
	}
};
</script>

<style>
</style>