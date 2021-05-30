<template>
	<div class="container">
		<div class="text-center mb-2">
			<img alt="Vue logo" src="../assets/logo.png">
			<HelloWorld msg="Vue.js는 가볍고 빠르다." />
		</div>
	</div>
</template>
<script>
import HelloWorld from '@/components/HelloWorld.vue';

export default {
	mounted() {    
		// 모든 화면이 렌더링된 후 실행합니다.
		this.$nextTick(function () {

			// this.$store.state.count++;
			// console.log("2 : ", this.$store.state.count);
			// this.$store.commit('increment');
			// console.log("3 : ", this.$store.state.count);
			// console.log("count : ", this.$store.state.count);
			// console.log("id : ", this.$store.state.id);
			// console.log("getId", this.$store.getters.getId);
		})
	},
	components: {
		HelloWorld
	},
};
</script>
<style>
</style>