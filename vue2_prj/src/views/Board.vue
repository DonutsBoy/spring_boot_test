<template>
    <v-container>
      <v-layout row wrap>
        <v-flex xs6>
          {{ todoList }}
        </v-flex>
        <v-flex xs6>
          <v-textarea
            outlined
            label="할일 입력후 버튼 클릭"
            value="33333"
          ></v-textarea>
          <v-btn>리스트 추가</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
</template>

<script>
export default {
  data() {
    return {
      todoList: ['11','11'],
    } 
  }

}
</script>

<style>
  .board {
    text-align: center;
  }
</style>