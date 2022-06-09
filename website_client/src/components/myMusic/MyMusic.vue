<template>
    <div class="out-display">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="page-header">
                        <h1>
                            <small>我的收藏</small>
                        </h1>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th></th>
                                <th>歌曲名称</th>
                                <th>歌曲创作人</th>
                                <th>歌曲专辑</th>
                                <th>播放音乐</th>
                                <th>移出收藏</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr v-for="(item, index) in song_info" :key="item.id">
                                <td>{{ index + 1 }}</td>
                                <td>{{ item.songName }}</td>
                                <td>{{ item.songSinger }}</td>
                                <td>{{ item.songAlbum }}</td>
                                <td>
                                    <i class="glyphicon glyphicon-play" :plain="true" ref="music_play"
                                        @click="music(isplay, index)"></i>
                                </td>
                                <td>
                                    <i class="el-icon-remove-outline" :plain="true"
                                        @click="editCollection(item.id)"></i>
                                </td>
                            </tr>
                        </tbody>

                    </table>
                </div>
            </div>

        </div>
    </div>

</template>

<script>
export default {
    inject: ['reload'],
    name: 'MyMusic',
    data() {
        return {
            song_info: [],
            isplay: false,
            nowIndex: '',
        }
    },
    methods: {
        // 获取歌曲资源路径
        getSongUrl(url) {
            return require('@/assets' + url);
        },
        // 我的歌单-取消收藏
        editCollection(id) {
            const _this = this
            axios.get('http://localhost:8081/collection/delete/' + id).then(function (resp) {
                if (resp.status === 200) {
                    _this.$message({
                        message: '取消收藏成功！',
                        type: 'success'
                    });
                    // 动态刷新当前页面
                    _this.reload()
                } else {
                    _this.$message.error('取消失败，请重新操作！');
                }

            })
        },
        // // 播放 / 暂停 音乐
        // music(play, index) {
        //     this.nowIndex = index;
        //     this.$store.commit('player/PLAYERINDEX', index);
        //     this.$bus.$emit("playMusic");
        //     if (play) { // 暂停音乐
        //         this.$store.commit('player/ISPLAY', false)
        //         this.$refs.song_list[index].classList.add('glyphicon-play');
        //         this.$refs.song_list[index].classList.remove('glyphicon-pause');
        //     } else { // 播放音乐
        //         this.$store.commit('player/ISPLAY', true)
        //         this.$refs.song_list[index].classList.add('glyphicon-pause');
        //         this.$refs.song_list[index].classList.remove('glyphicon-play');
        //     }
        // },
        // 播放 / 暂停 音乐
        music(play, index) {
            this.nowIndex = index;
            this.$store.commit('player/ISPLAY', play);
            this.$store.commit('player/PLAYERINDEX', index);
            this.$bus.$emit("playMusic");
            if (play) { // 暂停音乐
                // this.$refs.audio_music.pause();
                this.isplay = false;
                this.$refs.music_play[index].classList.add('glyphicon-play');
                this.$refs.music_play[index].classList.remove('glyphicon-pause');
            } else { // 播放音乐
                // this.$refs.audio_music.play();
                this.isplay = true;
                this.$refs.music_play[index].classList.add('glyphicon-pause');
                this.$refs.music_play[index].classList.remove('glyphicon-play');
            }
        },
    },
    // 我的歌单-展示全部歌单信息
    created() {
        const _this = this
        axios.get('http://localhost:8081/collection/displayall').then(function (res) {
            _this.song_info = res.data;
            localStorage.setItem("myMusicList", JSON.stringify(res.data)); // 将歌曲存储于浏览器
            console.log(res.data);
            // this.$store.commit('player/', res.);
        })
    },
    mounted() {
        // player组件被点击时，本组件同步暂停和播放
        this.$bus.$on("songListNextPlay", (newIndex) => {
            if (this.$refs.music_play[this.nowIndex].classList.contains('glyphicon-pause')) {
                this.$refs.music_play[this.nowIndex].classList.remove('glyphicon-pause');
                this.$refs.music_play[this.nowIndex].classList.add('glyphicon-play');
            }
            this.music(false, newIndex)

        })
        this.$bus.$on("songListPlay", (isPlay, newIndex) => {
            if (isPlay) {
                if (this.$refs.music_play[newIndex].classList.contains('glyphicon-pause')) {
                    this.$refs.music_play[newIndex].classList.add('glyphicon-play');
                    this.$refs.music_play[newIndex].classList.remove('glyphicon-pause');
                }
            } else {
                this.$refs.music_play[newIndex].classList.remove('glyphicon-play');
                this.$refs.music_play[newIndex].classList.add('glyphicon-pause');
            }
        })
    },
    beforeDestroy() {
        this.$bus.$off("songListNextPlay")
        this.$bus.$off("songListPlay")
    },


}
</script>

<style scoped>
h1 {
    text-align: center;
}

i {
    font-size: 20px;
}

td,
th {
    text-align: center;
    display: table-cell;
    /* 设置居中 */
    vertical-align: middle;
}

.out-display {
    height: 100vh;
    background: linear-gradient(90deg, #a7b59c, #d3d5c7);
}

.out-display::before {
    content: "";
    display: table;
}

.container {
    width: 80%;
    margin-top: 20px;
    border-radius: 30px;
    background: rgba(255, 255, 255, 0.9);
    box-shadow: 0 12px 45px #454343;
}

.page-header {
    margin: 20px 0 20px;
}
</style>