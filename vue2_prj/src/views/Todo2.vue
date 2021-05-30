<template>
  <div class="container">
    <div class="text-center mb-2">
      <img alt="Vue logo" src="../assets/logo.png">
    </div>
    <h2 class="text-center mb-4">todo 애플리케이션</h2>
    <input type="text" class="form-control" v-model="userInput" @keyup.enter="addTodo" placeholder="할일 입력후 엔터" ref="userInput">
    <!-- @keyup.enter 엔커티를 누르고나면 이벤트 발생 -->
    <div class="list-group mb-4"> <!-- mb-4 는 아래부분과 마진을 준다. -->
      <template v-for="(data, index) in activeTodoList">
        <Todo2List 
          :label="data.label" 
          @toggle-todo="toggleTodo(data)" 
          :key="index" 
        />
        <!-- data.label 값을 label 라는 파라미터로 보내주고 -->
        <!-- 발생시킨 toggle-todo 라는 신호를  받아서 toggleTodo 메소드를 실행한다. -->
      </template>
    </div>    
    <div class="text-right mb-4">
      <!--
      <div class="btn-group">
        <button type="button" class="btn btn-primary btn-sm" @click="changeState('active')">할일</button>
        <button type="button" class="btn btn-primary btn-sm" @click="changeState('done')">완료</button>
        <button type="button" class="btn btn-primary btn-sm" @click="changeState('all')">전체</button>
      </div>
      -->
      <b-form-radio-group v-model="selected">
        <b-form-radio value="active">할일</b-form-radio>
        <b-form-radio value="done">완료</b-form-radio>
        <b-form-radio value="all">전체</b-form-radio>
      </b-form-radio-group>
    </div>
    {{ todoList }}
    <br>
    {{ selected }}
  </div> 
</template>
<script>
import Todo2List from '@/components/Todo2List.vue';

export default {
  //메소드형태
  data() {
      return {
        selected: "active",
        userInput: "",
        todoList: [],
        currentState: "active",
      };
  },
  computed: {
    activeTodoList() {
      return this.todoList.filter(data => this.currentState === "all" || data.state === this.currentState);
    },
  },
  watch: {
    selected() {
      this.currentState = this.selected;
    },
  },
  methods: {
    changeState(state) {
      this.currentState = state;
    },
    addTodo() {
      if (this.userInput == "") {
        this.$refs.userInput.focus();
      } else {
        this.todoList.push({
          label: this.userInput,
          state: "active",
        });
        this.userInput = "";
      }
    },
    toggleTodo(data) {
      console.log(data.label);
      data.state = data.state === "active" ? "done" : "active";
    },
  },
  components: {
    Todo2List,
  },
}
</script>
<style>
</style>