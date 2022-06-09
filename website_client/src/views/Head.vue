<template>
    <div>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
            background-color="#51603f" style="opacity:0.9" text-color="#fff" active-text-color="#ffd04b" router>
            <el-menu-item index="/home"><i>C - Music</i></el-menu-item>
            <el-menu-item index="/home">首页</el-menu-item>
            <el-menu-item index="/songList">歌单</el-menu-item>
            <el-menu-item index="/singer">歌手</el-menu-item>
            <el-menu-item index="/myMusic">我的音乐</el-menu-item>
            <!-- 头像 -->
            <el-submenu index="" class="avatar">
                <template slot="title">
                    <el-avatar> {{ userName }} </el-avatar>
                </template>
                <el-menu-item index="/perInfo">个人信息</el-menu-item>
                <el-menu-item index="/myMusic">我的收藏</el-menu-item>
                <el-menu-item index="/" @click="layout">退出登陆</el-menu-item>
            </el-submenu>
        </el-menu>

    </div>
</template>

<script>
export default {
    name: 'Head',
    data() {
        return {
            songList: [],
            activeIndex: '1',
            userName: JSON.parse(localStorage.getItem('user')).uname,
        };
    },

    mounted() {
        this.$store.dispatch('song/getAllSongList');
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        layout() {
            localStorage.removeItem('user');
            localStorage.removeItem('songList');
        },
    }
}
</script>
<style scoped>
.avatar {
    float: right;
    margin-right: 10px;
}
</style>



