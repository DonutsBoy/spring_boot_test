<template>
    <button class="list-group-item text-left" @click="toggleTodo">
        {{ label }}
    </button>
</template>
<script>
export default {
    props: ['label'],
    methods: {
        toggleTodo() {
            //$emit 메소드로 부모의 이벤트를 발생시킨다.
            this.$emit('toggle-todo', "test");//이벤트를 발생
        }
    }
}
</script>
<style>
</style>