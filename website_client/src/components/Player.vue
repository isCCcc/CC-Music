<template>
    <div class="container">
        <!-- <div class="bg"></div>
        <div class="bg-mask"></div> -->
        <div class="player">
            <div class="player-track " ref="player_track">
                <div class="album-name">红模仿</div>
                <div class="track-name">周杰伦 - 红模仿</div>
                <div class="track-time">
                    <div class="current-time">00:00</div>
                    <div class="total-time">03:50</div>
                </div>
                <div class="progress-box">
                    <div class="hover-time">00:36</div>
                    <div class="hover-bar"></div>
                    <div class="progress-bar"></div>
                </div>
            </div>
            <div class="player-content">
                <div class="album-cover " ref="album_cover">
                    <img src="@/assets/img/singerPic/1560014170181tou.jpg" alt="" class="active">
                    <img src="@/assets/img/singerPic/1586075898639Celine Dion.png">
                    <img src="@/assets/img/singerPic/1586076393834胡伟立.png" alt="">
                    <img src="@/assets/img/singerPic/1586076633782五月天.jpg" alt="">
                    <img src="@/assets/img/singerPic/1586078551461Beyond.jpg" alt="">
                </div>
                <div class="play-controls">
                    <div class="control">
                        <div class="button play-prev">
                            <!-- <i class="fa fa-step-backward"></i> -->
                            <i class="glyphicon glyphicon-backward"></i>
                        </div>
                    </div>
                    <div class="control">
                        <div class="button play-pause" @click="playPause" ref="play_pause">
                            <!-- <i class="fa fa-play"></i> -->
                            <i class="glyphicon glyphicon-play" ref="play_pause_glyphicon"></i>
                        </div>
                    </div>
                    <div class="control">
                        <div class="button play-next">
                            <!-- <i class="fa fa-step-forward"></i> -->
                            <i class="glyphicon glyphicon-forward"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Plager',
    data() {
        return {
            isPlay: false,
        }
    },
    methods: {
        // 播放暂停
        playPause() {
            if (this.isPlay) {
                this.$refs.player_track.classList.remove('active');
                this.$refs.play_pause_glyphicon.classList = 'glyphicon glyphicon-play';
                this.$refs.album_cover.classList.remove('active');
                this.isPlay = false;
            } else {
                this.$refs.player_track.classList.add('active');
                this.$refs.play_pause_glyphicon.classList = 'glyphicon glyphicon-pause';
                this.$refs.album_cover.classList.add('active');
                this.isPlay = true;
            }
        }
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

/* 模糊背景图 */
.bg {
    position: fixed;
    top: -40px;
    bottom: -40px;
    right: -40px;
    left: -40px;
    background: url(../assets/img/singerPic/IU.jpg) no-repeat;
    background-size: cover;
    background-position: center;
    /* 滤镜模糊 */
    filter: blur(40px);
    z-index: 1;
}

/* 半透明白色遮罩层 */
.bg-mask {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(255, 255, 255, .5);
    z-index: 2;
}

.player {
    position: relative;
    z-index: 3;
    width: 100%;
    height: 100%;
    /* border: 1px solid black; */
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
    color: #ff668f;
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
    background-color: #ead2d7;
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
    background-color: #fd6d94;
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