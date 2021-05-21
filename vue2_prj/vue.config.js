module.exports = {
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",

  devServer: {  
    proxy: {
        //Vue.js devServer에서 요청경로가 /api 로 시작하면 프록시 설정해서 8080 포트로 보낸다.
        "/api": { 
          target: "http://localhost:8080",
          ws: true
        }
    }
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
