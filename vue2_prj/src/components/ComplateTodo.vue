<template>
    <div>
        {{ complateTitle }} : <span :style="complateTodoCnt > 0 ? 'color:#ff0000' : 'color:#000000'">{{ complateTodoCnt }}건</span> ,
        미완료 : <span :style="incomplateTodoCnt > 0 ? 'color:#0000ff' : 'color:#000000'">{{ incomplateTodoCnt }}건</span>
    </div>
</template>

<script>
export default {
    props: {
        todos: {
            type: Array,
            required: true,
        }
    },
    data() {
        return {
            complateTitle: "완료",
        }
    },
    computed: {
        complateTodoCnt() {
            return this.todos.filter(todo => todo.checked).length;
        },
        incomplateTodoCnt() {
            var cnt = 0;
            for(var i=0; i<this.todos.length; i++) 
            {
                if(this.todos[i].checked == false) {
                    cnt++;
                }
            }
            return cnt;
        },
    }
}
</script>

<style>

</style>