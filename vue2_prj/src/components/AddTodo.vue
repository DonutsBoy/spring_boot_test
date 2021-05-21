<template>
    <input 
      v-model="todoText"
      type="text" 
      class="w-100 p-2" 
      placeholder="할일을 입력후 엔터를 쳐주세요."
      @keyup.enter="addTodo"
      ref="todoText"
    >  
</template>

<script>
export default { 
    data() {
        return {
            todoText: "",
        }
    },
    methods: {
        addTodo(e) {
            this.$emit("add-todo", e.target.value);
        },
    }
}
</script>

<style>

</style>