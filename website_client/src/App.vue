<template>
  <div id="app">
    <!-- 标志路由显示区域 -->
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
// import Head from './views/head/Head';
// import Head from '@/views/head/Head';
export default {
  name: 'App',
  // components: { Head, },
  data() {
    return {
      isRouterAlive: true
    }
  },
  provide() {
    return {
      reload: this.reload
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    }
  }

}
</script>

<style scoped>
</style >