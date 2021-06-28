module.exports = {
  
  //vue.js 빌드 결과가 아래 경로에 생성된다.
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",
  
  //vue.config.js build할때만 public path 변경하기
  //publicPath: "/",
  
  devServer: {  
    proxy: {
      //Vue.js devServer에서 요청경로가 /api 로 시작하면 프록시 설정해서 8080 포트로 보낸다.
      "/api": { 
        target: "http://localhost:8080",
        ws: true
      }
    },    
  },

  chainWebpack: config => {  
    config
    .plugin('html')
    .tap(args => {
      args[0].title = 'Vue.js 스터디'
      return args
    })
    const svgRule = config.module.rule("svg");    
    svgRule.uses.clear();    
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");  
  },

};
