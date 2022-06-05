<template>
    <div>
        <!-- 轮播图 -->
        <div class="home">
            <el-carousel :interval="4000" type="card" height="300px">
                <el-carousel-item v-for="item in imgList" :key="item.id">
                    <img :src="item.idView" class="medium">
                </el-carousel-item>
            </el-carousel>
        </div>
        <!-- 歌单显示区域 -->
        <!-- <router-view></router-view> -->
        <SongList :songList="songList"></SongList>
    </div>
</template>

<script>
import Head from '@/views/Head';
import SongList from './SongList.vue';
export default {
    name: 'Home',
    components: { Head, SongList, SongList },
    data() {
        return {
            songList: JSON.parse(localStorage.getItem('songList')),
            imgList: [
                // 动态引入图片资源
                { id: 0, idView: require("@/assets/img/home/1.jpg") },
                { id: 1, idView: require("@/assets/img/home/2.jpg") },
                { id: 2, idView: require("@/assets/img/home/3.jpg") },
                { id: 3, idView: require("@/assets/img/home/4.jpg") },
                { id: 4, idView: require("@/assets/img/home/5.jpg") },
                { id: 5, idView: require("@/assets/img/home/6.jpg") },
            ]
        }
    },
    created() {
        // console.log(JSON.parse(localStorage.getItem('songList')))
    }
}
</script>

<style>
.el-carousel__item img {
    height: 100%;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}

.home {
    background: linear-gradient(200deg, #afafb3, #e2e2e0);
    box-shadow: 0 12px 45px #b7b6b6;
    border: 1px solid #99a9bf;
}
</style>