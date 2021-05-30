<template>
  <div class="container"><!-- 부트스트랩 class : container -->
    <div style="height:15px;"></div>
    <h1 class="text-center">할일 리스트</h1>    
    <ComplateTodo :todos="todos" />
    <AddTodo ref="AddTodoRef" @add-todo="addTodo"></AddTodo>
    <hr> 
    <TodoList 
      v-for="todo in todos" 
      :key="todo.id"
      :todo="todo"
      @toggle-checkbox="toggleCheckBox"
      @click-title="clickTitle"
      @click-delete="clickDelete"
    />
    <div>
      {{ todo_not_found_msg }}
    </div>
    <hr>
    <div style="text-align:center;">
      <b-button variant="primary" @click="btnAllDel">할일 전체삭제</b-button>
    </div>
    <div style="height:15px;"></div>
    <!--
    {{ todos }}
    -->
    <b-overlay :show="show" variant="secondary" opacity="0.40" no-wrap rounded="sm">
    </b-overlay>        
  </div>
</template>

<script>
import TodoList from "@/components/TodoList.vue";
import ComplateTodo from "@/components/ComplateTodo.vue";
import AddTodo from "@/components/AddTodo.vue";
// import axios from 'axios';

export default {
  mounted() {    
    this.$nextTick(function () {
      // 모든 화면이 렌더링된 후 실행합니다.
      this.select();
    })
  },
  components: {
    TodoList,
    ComplateTodo,
    AddTodo,
  },
  data() {
    return {
      show: false,
      todo_not_found_msg: "",
      todos: [
        // {
        //   id: 1,
        //   text: "사과",
        //   checked: true
        // },
        // {
        //   id: 2,
        //   text: "바나나",
        //   checked: false
        // },
      ]
    }
  },
  methods: {
    addTodo(s1) {

      //할일 내용 빈값 체크
      if(s1.trim() == "") {
        //부모->자식 제어하기
        this.$refs.AddTodoRef.todoText = "";
        this.$refs.AddTodoRef.todoText.focus();
        return true;
      }

      var param = {};
      param.TITLE = s1;
      param.CHECK_YN = "N";

      this.$axios.get("/api/todo/insert", {
        params: param
      }).then((response) => {        
        console.log(response.data);
        this.select();

        //ref 속성을 이용하여 부모에서 자식 엘리먼트에 접근하기
        this.$refs.AddTodoRef.todoText = "";

        this.$bvToast.toast("할일이 추가되었습니다.", {
            title: "처리 메시지",
            autoHideDelay: 1000,
            appendToast: true,
            solid: true,//solid true : 설정되면 반투명이 아닌 단색 배경으로 토스트를 렌더링합니다.
        });
      }).catch((error) => {
        console.log(error);
        alert(error);
      });

      // this.todos.push({
      //   id: Math.random(),
      //   text: e.target.value, 
      //   checked: false,
      // })    

    },
    select() {
      this.show = true;
      this.$axios.get("/api/todo/list", {
        params: {}
      }).then((response) => {

        //this.todos = response.data;
        this.todos = [];
        this.todo_not_found_msg = "";
        if(response.data.length == 0) {
          this.todo_not_found_msg = "등록된 할일이 없습니다.";
          return;
        }

        for(var i=0; i<response.data.length; i++) 
        {
          // this.todos.push(response.data[i]);            
          this.todos.push({
            id: response.data[i].ID,
            title: response.data[i].TITLE, 
            checked: response.data[i].CHECK_YN == "Y" ? true : false,
          });
        }  
        this.show = false;
      }).catch((error) => {
        console.log(error);
      });      
    },
    btnAllDel() {

      if(!confirm("등록된 할일을 전체 삭제하시겠습니까?")) {
        return;
      }

      this.$axios.get("/api/todo/allDelete", {
        params: {}
      }).then((response) => {
        
          this.select();
          console.log(response.data);

          this.$bvToast.toast("할일이 전체 삭제되었습니다.", {
            title: '메시지',
            autoHideDelay: 1000, 
            appendToast: false,
            solid: true,//solid true : 설정되면 반투명이 아닌 단색 배경으로 토스트를 렌더링합니다.
          });

      }).catch((error) => {
          alert(error);
          console.log(error);
      });  

    },
    toggleCheckBox({id, checked}) {
      
      let msg1 = "";
      let check_yn = (checked == true ? "Y" : "N");

      this.$axios.get("/api/todo/checkBoxUpdate", {
        params: {"id": id, "check_yn": check_yn}
      }).then((response) => {
          
          console.log(response.data);

          if(check_yn == "Y") {
            msg1 = "해당 할일을 완료했습니다.";
          } else {
            msg1 = "해당 할일을 완료취소했습니다.";
          }

          this.$bvToast.toast(msg1, {
            title: '메시지',
            autoHideDelay: 1000,
            appendToast: false,
            solid: true,//solid true : 설정되면 반투명이 아닌 단색 배경으로 토스트를 렌더링합니다.
          });

          //console.log(id, checked);

          const index = this.todos.findIndex(todo => {
            return todo.id === id;
          });      
          this.todos[index].checked = checked;

      }).catch((error) => {
          console.log(error);
          alert(error);
      });

    },
    clickTitle(id) {

      const index = this.todos.findIndex(todo => {
        return todo.id === id;
      });      
      this.todos[index].checked = !this.todos[index].checked;
            
      this.toggleCheckBox({"id": id, "checked": this.todos[index].checked});

    },
    clickDelete(id) {

      if(!confirm("해당 할일을 삭제하시겠습니까?")) {
        return;
      }

      this.$axios.get("/api/todo/delete", {
        params: {"id": id}
      }).then((response) => {

          this.select();
          console.log(response.data);

          this.$bvToast.toast("할일이 삭제되었습니다.", {
            title: '메시지',
            autoHideDelay: 1000,
            appendToast: false,
            solid: true,//solid true : 설정되면 반투명이 아닌 단색 배경으로 토스트를 렌더링합니다.
          });

      }).catch((error) => {
          console.log(error);
          alert(error);
      });

      // const index = this.todos.findIndex(todo => {
      //   return todo.id === id;
      // });      
      // this.todos.splice(index, 1);
      //또는
      //this.todos = thid.todos.filter(todo => todo.di !=== id);

    }
  }
}
</script>

<style>

</style>