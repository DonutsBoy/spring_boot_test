<template>
  <b-overlay :show="show" rounded="sm">
  <!--<div class="container">--><!-- 부트스트랩 class : container -->
  <b-container>
    <div style="height:15px;"></div>
    <h1 class="text-center">자동매매 일별수익</h1>    
    <div style="height:5px;"></div>
    <!-- <div style='text-align:center;color:#ff0000;'>
      {{$moment().format('YYYY년MM월DD일 dddd')}} 자동매매 진행중
    </div> -->
    <div style="text-align:right;">
      <b-button @click="select">조회하기</b-button>
    </div>
    <div>
      <b-form-group 
        label="세후손익금 구분" 
        label-cols-lg="2" 
        v-slot="{ ariaDescribedby }"
      >
        <b-form-radio-group
          :aria-describedby="ariaDescribedby"
          class="mt-lg-2"
          v-model="form1.profitLossGubun"
        >
          <!--<b-form-radio :value="null" inline>전체</b-form-radio>-->
          <b-form-radio value="" inline>전체</b-form-radio>
          <b-form-radio value="profit" inline>이익</b-form-radio>
          <b-form-radio value="loss" inline>손실</b-form-radio>
        </b-form-radio-group>
      </b-form-group>
      <!-- 
        responsive를 추가하면 반응형 테이블로 적용된다. 
        아이폰se1 웹브라우저에서 부트스트랩 테이블 항목수가 많으면 디스플레이 가로크기를 오버해서 출력한다. 가로 스크롤이 생긴다. 
        아이폰se1 웹브라우저에서 responsive 추가하면 디스플레이 가로크기에 핏하게 출력해서 가로 스크롤이 생기지 않는다. 대신 항목수가 많으면 잘려서 출력된다.
      -->
      <b-table 
        id="my-table" 
        hover 
        bordered
        head-variant="dark"
        :items="items" 
        :fields="fields" 
        :tbody-tr-class="rowClass" 
        responsive 
        :per-page="perPage"
        :current-page="currentPage"        
      >
        <template #cell(TRADE_PROFIT_LOSS_AFTER_TAX_AMT)="data">
          <span v-html="data.value"></span>
        </template>
        <template #cell(STOCK_RETURN_RATE)="data">
          <span v-html="data.value"></span>
        </template>
      </b-table>
    </div>
    <div style="height:5px;"></div>
    <div>
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
        align="center"
      ></b-pagination>    
    </div>
  </b-container>
  <!--</div>--><!-- 부트스트랩 class : container -->
  </b-overlay>
</template>

<script>
import axios from 'axios';

export default {
  // 실시간으로 계산, 로직을 화면에 출력할때 사용한다.
  computed: {
    rows() {
      return this.items.length
    },
  },
  // 인스턴스가 작성된 후 동기적으로 호출되고 마운트가 시작되지 않은 상태이다.
  // created 에 코드를 입력안하면 이런 에러가 뜬다. -> [Vue warn]: Error in created hook: "TypeError: handler.call is not a function"  
  // created: {
  // }, 
  // 인스턴스가 마운트 된 직후 호출됩니다. 
  mounted() {    
    // Vue.js에서 데이터갱신 후 UI까지 완료한 뒤에 nextTick에 있는 함수를 최종적으로 수행하게 된다.
    // 결론은 모든 화면이 렌더링된 후 실행합니다.
    // 모든 화면이 렌더링된 후 실행합니다.
    this.$nextTick(function () {
      console.log(navigator.userAgent.toLowerCase());
      var mobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));
      if (mobile) { 
        //모바일컴퓨터 테이블처리
        this.fields[1] = null;//숨기기
        this.fields[2].label = '세후손익금';
        //this.fields[3] = null;
        this.fields[3].label = '수익률';
        this.fields[4] = null;//숨기기

        this.perPage = 5; //모바일컴퓨터에서는 페이지별 게시물갯수
      }else{
        //피씨컴퓨터 테이블처리
      }      
      this.select();
    }) 
  },
  components: {
    // TodoList,
    // AddTodo,
  },
  data() {
    return {
      form1: {
        profitLossGubun: '',
        fileSelf:null,
      },      
      perPage: 10,
      currentPage: 1,      
      show: false,
      fields: [
        { 
          key: 'TRADE_DT2',
          label: '거래일자',
          sortable: false,
          class: 'text-center'
        },
        {
          key: 'TRADE_AMT',
          label: '매수금액 (원)',
          sortable: false,
          class: 'text-right',
          formatter: value => {
            //천단위 콤마표시
            var value2 = value;
            value2 = String(value).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            return value2;
          },
        },
        {
          key: 'TRADE_PROFIT_LOSS_AFTER_TAX_AMT',
          label: '세후손익금 (원)',
          sortable: false,
          class: 'text-right',
          formatter: value => {
            //천단위 콤마표시
            var value2 = value;
            value2 = String(value).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            if (value > 0) {
              value2 = "<font color=red>"+value2+"</font>";
            } else if (value < 0) {
              value2 = "<font color=blue>"+value2+"</blue>";
            }
            return value2;
          },          
        },
        {
          key: 'STOCK_RETURN_RATE',
          label: '수익률 (%)',
          sortable: false,
          class: 'text-right',
          formatter: value => {
            if (value > 0) {
              return "<font color=red>"+value+"</font>";
            } else if (value < 0) {
              return "<font color=blue>"+value+"</blue>";
            } else {
               return value;
            }
          },          
        },
        {
          key: 'TRADE_CNT',
          label: '거래횟수 (회)',
          sortable: false,
          class: 'text-right'
          // Variant applies to the whole column, including the header and footer
          //variant: 'danger'
        }
      ],
      items: [
        // { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
        // { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
        // { age: 89, first_name: 'Geneva', last_name: 'Wilson' },
        // { age: 38, first_name: 'Jami', last_name: 'Carney' }
      ],
      //todo_not_found_msg: "현재 자동매매중..",
      
    }
  }, 
  methods: {
    rowClass(item, type) {
      var moment = require('moment'); // require
      var today = moment().format('YYYY-MM-DD')
      
      if (!item || type !== 'row') return
      if (item.TRADE_DT2 === today) return 'table-success'
    },    
    select() {

      console.log(this.form1.profitLossGubun);

      this.show = true;

      var param = {};
      param.profitLossGubun = this.form1.profitLossGubun;//세후손익금구분

      axios.get("/api/auto/dayProfit/select", {
        params: param
      }).then((response) => {

        this.items = response.data;
        
        this.show = false;

      }).catch((error) => {
        console.log(error);
      });      
    },
  }
}
</script>

<style>
</style>