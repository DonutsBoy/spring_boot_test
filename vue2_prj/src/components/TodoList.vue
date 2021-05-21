<template>
    <div class="mb-2 d-flex">
        <div>
            <input 
                type="checkbox" 
                :checked="todo.checked" 
                @change="toggleCheckBox"
            >
        </div>
        <!-- {{ todo.checked }} -->
        <span 
            class="ml-3 flex-grow-1"
            :class="todo.checked ? 'text-muted' : ''"
            :style="todo.checked ? 'text-decoration: line-through' : ''"            
            @click="clickTitle"
        >
            {{ todo.title }}
        </span>
        <div>
            <button class="btn btn-danger btn-sm" style="white-space:nowrap;" @click="clickDelete">삭제</button>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        todo: {
            type: Object,
            required: true,
        }
    },
    methods: {
        toggleCheckBox(e) {
            this.$emit("toggle-checkbox", {
                id: this.todo.id,
                checked: e.target.checked
            });
        },
        clickTitle() {
            this.$emit("click-title", this.todo.id);
        },        
        clickDelete() {
            this.$emit("click-delete", this.todo.id);
        }
    }
}
</script>

<style>

</style>