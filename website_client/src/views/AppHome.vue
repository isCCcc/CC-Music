<template>
    <div>

        <Head></Head>

        <!-- 歌单显示区域 -->
        <router-view></router-view>
        <Player></Player>
    </div>
</template>

<script>
import Head from '@/views/Head';
import Player from '@/components/Player';
export default {
    name: 'AppHome',
    components: { Head, Player, },
    created() {
        // 通过判断localStorage是否为空来辨别用户是否登录
        if (localStorage.getItem('user') == null) {
            this.$router.push('/')
            alert("请先登录账号！")
        }
    },

}
</script>

