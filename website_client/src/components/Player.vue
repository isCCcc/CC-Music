<template>
    <div class="container">
        <audio :src="getSongUrl(songList[songIndex].url)" controls="controls" ref="audio_music" style="display:none"
            autoplay muted @timeupdate="updateCurTime"></audio>
        <div class="player">
            <div class="player-track " ref="player_track">
                <div class="album-name">{{ songList[songIndex].name }}</div>
                <div class="track-name">{{ songList[songIndex].introduction }}</div>
                <div class="track-time">
                    <div class="current-time" ref="current_time">00:00</div>
                    <div class="total-time" ref="total_time">03:50</div>

                </div>
                <div class="progress-box" @click="playFromClickedPos" @mousemove="showHover" @mouseout="hideHover"
                    ref="progress_box">
                    <div class="hover-time" ref="hover_time">00:36</div>
                    <div class="hover-bar" ref="hover_bar"></div>
                    <div class="progress-bar" ref="progress_bar"></div>
                </div>
            </div>
            <div class="player-content">
                <div class="album-cover " ref="album_cover">
                    <img :src="attachImageUrl(songList[songIndex].pic)" class="active">
                </div>
                <div class="play-controls">
                    <div class="control">
                        <div class="button play-prev" @click="playNext(songIndex - 1)">
                            <i class="glyphicon glyphicon-backward"></i>
                        </div>
                    </div>
                    <div class="control">
                        <div class="button play-pause" @click="playPause" ref="play_pause">
                            <i class="glyphicon glyphicon-play" ref="play_pause_glyphicon"></i>
                        </div>
                    </div>
                    <div class="control">
                        <div class="button play-next" @click="playNext(songIndex + 1)">
                            <i class="glyphicon glyphicon-forward"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
    name: 'Player',
    inject: ['reload'],
    computed: {
        ...mapState('song', ['songList']),
        ...mapState('player', ['isPlay', 'songIndex',
            'progressT', 'progressLoc', 'cm', 'ctMinutes', 'ctSeconds',
            'curMinutes', 'curSeconds', 'durMinutes', 'durSeconds', 'playProgress']),
    },
    data() {
        return {
            isNotMyMusic: true // 是否从MyMusic跳转过来
        }
    },
    methods: {
        // 播放暂停
        playPause() {
            this.$bus.$emit("songListPlay", this.isPlay, this.songIndex)
            if (this.isPlay) { //暂停音乐
                this.$refs.player_track.classList.remove('active');
                this.$refs.play_pause_glyphicon.classList = 'glyphicon glyphicon-play';
                this.$refs.album_cover.classList.remove('active');
                this.$store.commit('player/ISPLAY', false);
                setTimeout(() => {
                    this.$refs.audio_music.pause();
                }, 300)
            } else { // 播放音乐
                this.$refs.player_track.classList.add('active');
                this.$refs.play_pause_glyphicon.classList = 'glyphicon glyphicon-pause';
                this.$refs.album_cover.classList.add('active');
                this.$store.commit('player/ISPLAY', true);
                setTimeout(() => {
                    this.$refs.audio_music.play();
                }, 300);
            }
        },
        // 播放 下一首 / 上一首
        playNext(Index) {
            if (Index > this.songList.length - 1 || Index < 0) { // 下标越界
                if (Index < 0) {
                    Index = this.songList.length - 1
                } else {
                    Index = 0;
                }

                this.$store.commit('player/NEXTINDEX', Index);
            }
            // console.log(this.songList.length)
            // 触发SongList / MyMusic组件里的播放和暂停
            this.$bus.$emit("songListNextPlay", Index)
            this.$refs.progress_bar.style.width = '0px';
            this.$refs.current_time.innerText = '00:00';
            this.$refs.total_time.innerText = '00:00';
            this.$store.commit('player/NEXTINDEX', Index);
            this.$store.commit('player/ISPLAY', false);
            this.$refs.audio_music.play();
            this.playPause();
        },
        // 进度条鼠标移入事件：显示悬停播放位置弹窗
        showHover(e) {
            // 计算鼠标在进度条上的悬停位置(当前鼠标的X坐标-进度条在窗口中的left位置)
            this.$store.commit('player/SETPROGRESST', e.clientX - this.$refs.progress_box.getBoundingClientRect().left)
            // 计算鼠标在进度条上悬停时的音频位置
            // 获取音频总时长:this.$refs.audio_music.duration
            this.$store.commit('player/SETPROGRESSLOC', this.$refs.audio_music.duration * (this.progressT / this.$refs.progress_box.getBoundingClientRect().width))
            // 设置悬停进度条的宽度
            this.$refs.hover_bar.style.width = this.progressT + 'px';
            // 将悬停音频的位置转换为分钟
            this.$store.commit('player/SETCM', this.progressLoc / 60);
            this.$store.commit('player/SETCTMINUTES', Math.floor(this.cm))
            this.$store.commit('player/SETCTSECONDS', Math.floor(this.progressLoc - this.ctMinutes * 60))

            if (this.ctMinutes < 10) {
                this.$store.commit('player/SETCTMINUTES', '0' + this.ctMinutes)
            }
            if (this.ctSeconds < 10) {
                this.$store.commit('player/SETCTSECONDS', '0' + this.ctSeconds)
            }
            if (isNaN(this.ctMinutes) || isNaN(this.ctSeconds)) {
                this.$refs.hover_time.innerText = '--:--';
            } else {
                this.$refs.hover_time.innerText = this.ctMinutes + ':' + this.ctSeconds;
            }

            // 设置悬停播放位置弹层的位置并显示
            this.$refs.hover_time.style.left = this.progressT + 'px';
            this.$refs.hover_time.style.marginLeft = '-20px';
            this.$refs.hover_time.style.display = 'block';

        },
        // 进度条鼠标移出事件：隐藏悬停播放弹层
        hideHover() {
            this.$refs.hover_bar.style.width = '0px';
            this.$refs.hover_time.innerText = '00:00';
            this.$refs.hover_time.style.left = '0px';
            this.$refs.hover_time.style.marginLeft = '0px';
            this.$refs.hover_time.style.display = 'none';
        },
        // 进度条鼠标点击事件：从点击的位置开始播放
        playFromClickedPos() {
            // 设置当前播放时间
            console.log(this.$refs.audio_music.currentTime)
            this.$refs.audio_music.currentTime = this.progressLoc;
            // 设置进度条宽度
            this.$refs.progress_bar.style.width = this.progressT + 'px';
            // 隐藏悬停播放位置弹层
            this.hideHover();
        },
        // 改变当前播放时间
        updateCurTime() {
            // 当前播放时间(分)
            // curminutes = Math.floor(this.$refs.audio_music.currentTime / 60);
            this.$store.commit('player/SETCURMINUTES', Math.floor(this.$refs.audio_music.currentTime / 60))
            // 当前播放时间(秒)
            // curseconds = Math.floor(this.$refs.audio_music.audio.currentTime - this.curMinutes * 60);
            this.$store.commit('player/SETCURSECONDS', Math.floor(this.$refs.audio_music.currentTime - this.curMinutes * 60))
            // 音频总时长(分)
            // durminutes = Math.floor(this.$refs.audio_music.duration / 60);
            this.$store.commit('player/SETDURMINUTES', Math.floor(this.$refs.audio_music.duration / 60));
            // 音频总时长(秒)
            // durSeconds = Math.floor(this.$refs.audio_music.duration - this.durMinutes * 60);
            this.$store.commit('player/SETDURSECONDS', Math.floor(this.$refs.audio_music.duration - this.durMinutes * 60));
            // 计算播放进度
            // play_progress = this.$refs.audio_music.currentTime / this.$refs.audio_music.duration * 100;
            this.$store.commit('player/SETPLAYPROGRESS', this.$refs.audio_music.currentTime / this.$refs.audio_music.duration * 100);

            if (this.curMinutes < 10) {
                // this.curMinutes = '0' + this.curMinutes;
                this.$store.commit('player/SETCURMINUTES', '0' + this.curMinutes);
            }
            if (this.curSeconds < 10) {
                // this.curSeconds = '0' + this.curSeconds;
                this.$store.commit('player/SETCURSECONDS', '0' + this.curSeconds);
            }
            if (this.durMinutes < 10) {
                // this.durMinutes = '0' + this.durMinutes;
                this.$store.commit('player/SETDURMINUTES', '0' + this.durMinutes);
            }
            if (this.durSeconds < 10) {
                // this.durSeconds = '0' + this.durSeconds;
                this.$store.commit('player/SETDURSECONDS', '0' + this.durSeconds);
            }

            // 设置播放时间
            if (isNaN(this.curMinutes) || isNaN(this.curSeconds)) {
                this.$refs.current_time.innerText = '00:00';
            } else {
                this.$refs.current_time.innerText = this.curMinutes + ':' + this.curSeconds;
            }
            // 设置总时长
            if (isNaN(this.durMinutes) || isNaN(this.durSeconds)) {
                this.$refs.total_time.innerText = '00:00';
            } else {
                this.$refs.total_time.innerText = this.durMinutes + ':' + this.durSeconds;
            }
            // 设置进度条宽度
            this.$refs.progress_bar.style.width = this.playProgress + '%';

            // 播放完毕, 恢复样式
            if (this.playProgress == 100) {
                this.$refs.play_pause_glyphicon.classList = 'glyphicon glyphicon-play';
                this.$refs.progress_bar.style.width = '0px';
                this.$refs.current_time.innerText = '00:00';
                this.$refs.player_track.classList.remove('active');
                this.$refs.album_cover.classList.remove('active');
            }
        },
        // 获取图片的地址
        attachImageUrl(url) {
            return require("@/assets" + url);
        },
        // 获取音乐资源
        getSongUrl(url) {
            return require("@/assets" + url);
        },
    },
    mounted() {
        // 通$on接收传过来的事件
        this.$bus.$on("playMusic", () => {
            this.playPause()
        })
    },
    beforeDestroy() {
        this.$bus.$off('playMusic');
    },

}
</script>

<style scoped>
* {
    padding: 0;
    margin: 0;
}

.container {
    width: 240px;
    height: 70px;
    position: fixed;
    right: 5px;
    bottom: 30px;
}

.player {
    position: relative;
    z-index: 3;
    width: 100%;
    height: 100%;
}

/* 歌曲信息轨道区域 */
.player-track {
    position: absolute;
    top: 0;
    right: 9px;
    bottom: 0;
    left: 9px;
    /* 顺时针方向:上右下左 */
    padding: 7px 13px 6px 110px;
    background-color: rgba(255, 255, 255, .8);
    border-radius: 9px 9px 0 0;
    /* 改变top时的过渡效果 */
    transition: top 0.3s ease;
}

/* 歌曲信息轨道区域活动态 */
.player-track.active {
    /* 上移 */
    top: -70px;
}

.album-name {
    color: #333;
    font-size: 12px;
    font-weight: bold;
}

.track-name {
    color: #888;
    font-size: 10px;
    margin: 2px 0 5px 0;
}

.track-time {
    height: 10px;
    line-height: 10px;
    margin-bottom: 1px;
    overflow: hidden;
}

.current-time,
.total-time {
    color: #51603f;
    font-size: 10px;
    transition: 0.3s ease;

}

.current-time {
    float: left;
}

.total-time {
    float: right;
}

/* 播放进度区域 */
.progress-box {
    position: relative;
    height: 4px;
    background-color: #d3d5c7;
    border-radius: 4px;
    cursor: pointer;
}

/* 悬停进度条显示 */
.hover-time {
    position: absolute;
    top: -30px;
    background-color: rgba(0, 0, 0, 0.8);
    color: #fff;
    font-size: 12px;
    padding: 5px 6px;
    border-radius: 4px;
    display: none;
}

/* 悬停进度条颜色 */
.hover-bar {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 2;
    background-color: rgba(0, 0, 0, 0.1);
    border-radius: 4px;
}

/* 已播放的进度条颜色 */
.progress-bar {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 1;
    background-color: #516041;
    border-radius: 4px;
    width: 0;
    /* width改变时的过渡效果 */
    transition: width 0.2s ease;

}

.player-content {
    position: relative;
    height: 100%;
    background-color: #fff;
    border-radius: 15px;
    z-index: 2;
    box-shadow: 0 12px 45px #b7b6b6;
}

/* 封面 */
.album-cover {
    width: 82px;
    height: 82px;
    border-radius: 50%;
    position: absolute;
    top: -24px;
    left: 20px;
    box-shadow: 10px 0 10px #333;
    overflow: hidden;
    transition: 0.3s ease;
}

/* 唱片中间的小圆点 */
.album-cover::before {
    content: "";
    width: 16px;
    height: 16px;
    background-color: #d6dee6;
    position: absolute;
    top: 50%;
    right: 0;
    bottom: 0;
    left: 0;
    border-radius: 50%;
    margin: -8px auto auto auto;
    box-shadow: inset 0 0 0 2px #fff;
    z-index: 1;
}

/* 封面的活动态 */
.album-cover.active {
    top: -42px;
    box-shadow: 0 0 4px #fff7f7,
        30px 50px -15px #afb7c1;
}

.album-cover img {
    display: block;
    width: 100%;
    height: 0%;
    object-fit: cover;
    opacity: 0;
}

.album-cover img.active {
    opacity: 1;
    height: 100%;
}

/* 播放时封面旋转 */
.album-cover.active img.active {
    /* 执行动画：动画名 时长 线性的 无限次播放 */
    animation: rotateAlbumCover 3s linear infinite;
}

/* 控制区 */
.play-controls {
    width: 140px;
    height: 100%;
    /* border: 1px solid red; */
    float: right;
    overflow: hidden;
    display: flex;
    align-items: center;
    padding-right: 2px;
}

.control {
    flex: 1;
}

.control .button {
    width: 44px;
    height: 45px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fff;
    border-radius: 6px;
    /* border: 1px solid blueviolet; */
    cursor: pointer;
    transition: 0.2s ease;
}

.control .button i {
    color: #d6dee6;
    font-size: 20px;
    transition: 0.2s ease;
}

.control .button:hover {
    background-color: #d6dee6;
}

.control .button:hover i {
    color: #fff;
}

/* 自定义动画 */
@keyframes rotateAlbumCover {
    0% {
        transform: rotateZ(0);
    }

    100% {
        transform: rotateZ(360deg);
    }
}
</style>