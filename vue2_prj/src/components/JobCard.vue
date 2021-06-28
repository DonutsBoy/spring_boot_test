<template>
	<div class="hello">
		<!-- md : Small (sm) ≥576px -->
		<b-col>
			<b-card
			  :title="name"
			  img-src="https://picsum.photos/600/300/?image=25"
			  img-alt="Image"
			  img-top
			  tag="article"
			  style="max-width: 20rem;"
			  class="mb-2"
			  footer="Card Footer"
			>
				<b-card-text>
				    Some quick example text to build on the card title and make up the bulk of the card's content.
				</b-card-text>
				<b-button href="#" variant="primary">Go somewhere</b-button>
			</b-card>
		</b-col>
	</div>
</template>

<script>
export default {
	name: 'HelloWorld',
 	props: ["name"],
}
</script>

<style scoped>
</style>
