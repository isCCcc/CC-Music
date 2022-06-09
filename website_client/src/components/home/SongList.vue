<template>
    <div class="out-display">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="page-header">
                        <h1>
                            <small style="text-align:center">热门歌单</small>
                        </h1>
                    </div>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>歌曲排行</th>
                                <th>播放音乐</th>
                                <th>歌曲名称</th>
                                <th>歌曲专辑</th>
                                <th>收藏音乐</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr v-for="(song, index) in songList" :key="song.id">
                                <td>{{ index + 1 }}</td>
                                <td>
                                    <i class="glyphicon glyphicon-play" :plain="true" @click="music(isPlay, index)"
                                        ref="song_list"></i>
                                </td>
                                <td>{{ song.name }}</td>
                                <td>{{ song.introduction }}</td>
                                <td><i class="glyphicon glyphicon-star-empty" @click="collect(iscollect, index)"
                                        ref="collect_music" style="font-size:20px"></i></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import request from "@/util/request";
import { mapState } from "vuex";
export default {
    inject: ['reload'],
    name: 'SongList',
    data() {
        return {
            nowIndex: '',
            iscollect: false, // 收藏歌曲
        }
    },
    computed: {
        ...mapState('song', ['songList']),
        ...mapState('player', ['isPlay']),
    },
    methods: {
        // 播放 / 暂停 音乐
        music(play, index) {
            this.nowIndex = index;
            this.$store.commit('player/PLAYERINDEX', index);
            this.$bus.$emit("playMusic");
            if (play) { // 暂停音乐
                this.$store.commit('player/ISPLAY', false)
                this.$refs.song_list[index].classList.add('glyphicon-play');
                this.$refs.song_list[index].classList.remove('glyphicon-pause');
            } else { // 播放音乐
                this.$store.commit('player/ISPLAY', true)
                this.$refs.song_list[index].classList.add('glyphicon-pause');
                this.$refs.song_list[index].classList.remove('glyphicon-play');
            }
        },
        // 收藏歌曲
        collect(iscollect, index) {
            let _this = this
            if (iscollect) {
                this.$refs.collect_music[index].classList.remove('glyphicon-star');
                this.$refs.collect_music[index].classList.add('glyphicon-star-empty');
                this.iscollect = false;
                request.post('/song/deleteFromCollection', null, {
                    params: {
                        sname: _this.songList[index].name,
                    }
                }).then(res => {
                    if (res.code === '200') {
                        this.$message.success('已取消收藏');
                    } else {
                        this.$message.error(res.msg)
                    }
                }).catch(error => {
                    this.$message.error(error.msg)
                })


            } else {
                this.$refs.collect_music[index].classList.remove('glyphicon-star-empty');
                this.$refs.collect_music[index].classList.add('glyphicon-star');
                this.iscollect = true;
                request.post('/song/addToCollection', null, {
                    params: {
                        uid: JSON.parse(localStorage.getItem('user')).uid,
                        sname: _this.songList[index].name,
                        ssinger: _this.songList[index].singerId,
                        salbum: _this.songList[index].introduction,
                        surl: _this.songList[index].url
                    }
                }).then(res => {
                    if (res.code === '200') {
                        this.$message.success('已收藏');
                    } else {
                        this.$message.error(res.msg)
                    }
                }).catch(error => {
                    this.$message.error(error.msg)
                })
            }
        }

    },
    mounted() {
        // player组件被点击时，本组件同步暂停和播放
        this.$bus.$on("songListNextPlay", (newIndex) => {
            if (this.$refs.song_list[this.nowIndex].classList.contains('glyphicon-pause')) {
                this.$refs.song_list[this.nowIndex].classList.remove('glyphicon-pause');
                this.$refs.song_list[this.nowIndex].classList.add('glyphicon-play');
            }
            this.music(false, newIndex)

        })
        this.$bus.$on("songListPlay", (isPlay, newIndex) => {
            if (isPlay) {
                if (this.$refs.song_list[newIndex].classList.contains('glyphicon-pause')) {
                    this.$refs.song_list[newIndex].classList.add('glyphicon-play');
                    this.$refs.song_list[newIndex].classList.remove('glyphicon-pause');
                }
            } else {
                this.$refs.song_list[newIndex].classList.remove('glyphicon-play');
                this.$refs.song_list[newIndex].classList.add('glyphicon-pause');
            }
        })
    },
    beforeDestroy() {
        this.$bus.$off("songListPlay");
        this.$bus.$off("songListNextPlay");
    },
}
</script>

<style scoped>
.out-display {
    min-height: 100vh;
    background: linear-gradient(90deg, #a7b59c, #d3d5c7);
    /* background-color: #; */
}

.out-display::before {
    content: "";
    display: table;
}

.container {
    /* border: 1px solid red; */
    vertical-align: middle;
    width: 950px;
    height: 95%;
    margin-top: 20px;
    border-radius: 30px;
    background-color: #fff;
    box-shadow: 0 12px 45px #332929;
}

td,
th {
    text-align: center;
    display: table-cell;
    /* 设置居中 */
    vertical-align: middle;
}

.page-header {
    margin: 20px 0 20px;
}
</style>
