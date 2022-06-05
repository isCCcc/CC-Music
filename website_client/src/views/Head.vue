<template>
    <div>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
            background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" router>
            <el-menu-item index="/home"><i>CC - Music</i></el-menu-item>
            <el-menu-item index="/home">首页</el-menu-item>
            <el-menu-item @click="songlist">歌单</el-menu-item>
            <el-menu-item index="/singer">歌手</el-menu-item>
            <el-menu-item index="/myMusic">我的音乐</el-menu-item>
            <!-- 头像 -->
            <el-submenu index="" class="avatar">
                <template slot="title">
                    <el-avatar> {{ userName }} </el-avatar>
                    <!-- <el-button type="primary">{{ userName }}</el-button> -->
                </template>
                <el-menu-item index="/perInfo" style="width: 20px">个人信息</el-menu-item>
                <el-menu-item index="/myMusic" style="width: 20px">我的收藏</el-menu-item>
                <el-menu-item index="/" @click="layout">退出登陆</el-menu-item>
            </el-submenu>
        </el-menu>

    </div>
</template>

<script>
import request from "@/util/request"
export default {
    name: 'Head',
    data() {
        return {
            songList: [],
            activeIndex: '1',
            userName: JSON.parse(localStorage.getItem('user')).uname,
        };
    },
    created() {
        // 避免重复发起请求
        if (localStorage.getItem('songList')) {
            console.log("songList不为空")
            console.log(JSON.parse(localStorage.getItem('songList')))
            this.songList = JSON.parse(localStorage.getItem('songList'))
            console.log(this.songList)

        } else {
            console.log("songList为空")
            let _this = this
            request.get('/song/all').then(res => {
                if (res.code === '200') {
                    _this.songList = res.data
                    console.log(_this.songList[0])
                    localStorage.setItem("songList", JSON.stringify(res.data)); // 将歌曲存储于浏览器
                } else {
                    this.$message.error(res.msg)
                }
            }).catch(error => {
                this.$message.error(error.msg)
            })
        }
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        layout() {
            localStorage.removeItem('user');
            localStorage.removeItem('songList');
        },
        songlist() {
            //利用$router.push进行跳转        
            this.$router.push({
                //path后面跟跳转的路由地址
                path: '/songList',
                //name后面跟跳转的路由名字（必须有亲测，不使用命名路由会传参失败）
                name: 'songList',
                params: {
                    //singerList是自己定义的名字，item是要被传递的值
                    songList: this.songList,
                }
            })
        }
    }
}
</script>
<style scoped>
.avatar {
    float: right;
    margin-right: 10px;
}
</style>



