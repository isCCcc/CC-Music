const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // // 前端解决跨域问题
  // devServer: {
  //   proxy: {
  //     '/login': {// 匹配所有以 '/'开头的请求路径
  //       target: 'http://localhost:8081',// 代理目标的基础路径
  //       changeOrigin: true,
  //       // pathRewrite: {'^/': ''}
  //     },
  //   },
  //   headers: {
  //     'Access-Control-Allow-Origin': '*',
  //   }
  // }

})

// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   devServer: {
//     host: '0.0.0.0',
//     // https:true,
//     port: 6103,
//     client: {
//       webSocketURL: 'ws://0.0.0.0:6103/ws',
//     },
//     headers: {
//       'Access-Control-Allow-Origin': '*',
//     }
//   },

//   transpileDependencies: true
// })