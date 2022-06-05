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
            <audio src="@/assets/song/IU-Someday.mp3" controls="controls" ref="audio_music"
                style="display:none"></audio>
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
        }
    },
    methods: {
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
        // 播放 / 暂停 音乐
        music(play, index) {
            if (play) { // 暂停音乐
                this.$refs.audio_music.pause();
                this.isplay = false;
                this.$refs.music_play[index].classList.add('glyphicon-play');
                this.$refs.music_play[index].classList.remove('glyphicon-pause');
            } else { // 播放音乐
                this.$refs.audio_music.play();
                this.isplay = true;
                this.$refs.music_play[index].classList.add('glyphicon-pause');
                this.$refs.music_play[index].classList.remove('glyphicon-play');
            }
        },
    },
    // 我的歌单-展示全部歌单信息
    created() {
        const _this = this
        axios.get('http://localhost:8081/collection/displayall').then(function (resp) {
            _this.song_info = resp.data;
            // console.log(resp.data)
        })
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
</style>