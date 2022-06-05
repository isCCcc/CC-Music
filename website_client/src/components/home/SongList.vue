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
                            <tr v-for="(song, index) in (newSongList || songList)" :key="song.id">
                                <td>{{ index + 1 }}</td>
                                <td>
                                    <i class="glyphicon glyphicon-play" :plain="true" @click="music(isplay, index)"
                                        ref="song_list"></i>
                                </td>
                                <td>{{ song.name }}</td>
                                <td>{{ song.introduction }}</td>
                                <td><i class="glyphicon glyphicon-star-empty" @click="collect(iscollect, index)"
                                        ref="collect_music" style="font-size:20px"></i></td>

                                <audio :src="getSongUrl(song.url)" controls="controls" ref="audio_music"
                                    style="display:none"></audio>
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
export default {
    inject: ['reload'],
    name: 'SongList',
    props: {
        songList: {
            type: Array,
            // default: function () { return [] }
            default: () => []
        },
    },
    data() {
        return {
            newSongList: [],
            // songList: [],
            isplay: false, // 歌曲播放
            iscollect: false, // 收藏歌曲
        }
    },
    methods: {
        //获取歌曲地址
        getSongUrl(url) {
            return require('@/assets' + url);
        },
        // 播放 / 暂停 音乐
        music(play, index) {
            if (play) { // 暂停音乐
                this.$refs.audio_music[index].pause();
                this.isplay = false;
                this.$refs.song_list[index].classList.add('glyphicon-play');
                this.$refs.song_list[index].classList.remove('glyphicon-pause');
            } else { // 播放音乐
                this.$refs.audio_music[index].play();
                this.isplay = true;
                this.$refs.song_list[index].classList.add('glyphicon-pause');
                this.$refs.song_list[index].classList.remove('glyphicon-play');
            }
        },
        // 收藏歌曲
        collect(iscollect, index) {
            if (this.newSongList) {//判断是否从head跳过来
                console.log(this.newSongList)
                console.log("from head")
                // this.newSongList = songList
            } else {
                console.log("not from head")
                this.newSongList = this.songList
            }

            let _this = this
            if (iscollect) {
                this.$refs.collect_music[index].classList.remove('glyphicon-star');
                this.$refs.collect_music[index].classList.add('glyphicon-star-empty');
                this.iscollect = false;
                request.post('/song/deleteFromCollection', null, {
                    params: {
                        sname: _this.newSongList[index].name,
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
                console.log(this.newSongList[index])
                request.post('/song/addToCollection', null, {
                    params: {
                        uid: JSON.parse(localStorage.getItem('user')).uid,
                        sname: _this.newSongList[index].name,
                        ssinger: _this.newSongList[index].singerId,
                        salbum: _this.newSongList[index].introduction,
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
    created() {
        //接收 Head 传过来的参数
        this.newSongList = this.$route.params.songList
    },
}
</script>

<style scoped>
.out-display {
    background: linear-gradient(200deg, #e2e2e5, #e2e2e0);
}

.container {
    /* border: 1px solid red; */
    vertical-align: middle;
    width: 950px;
    /* margin-top: 30px; */
    background-color: #fff;
    box-shadow: 0 12px 45px #b7b6b6;
}

td,
th {
    text-align: center;
    display: table-cell;
    /* 设置居中 */
    vertical-align: middle;
}
</style>
