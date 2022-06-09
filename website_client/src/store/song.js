import request from "@/util/request"

export default {
    namespaced: true,
    state: {
        songList: [],      // 歌曲列表
        singerSongList: [], // 单个歌手的所有歌曲
        message: '',       // 出错信息
    },
    getters: {
        // songList: (state) => { JSON.parse(localStorage.getItem('songList')) }
    },
    actions: {
        // 向后台请求全部歌单数据
        getAllSongList(context) {
            request.get('/song/all').then(res => {
                if (res.code === '200') {
                    console.log("请求成功");
                    context.commit('SETLOCALSONGLIST', res.data);
                } else {
                    console.log("请求后台数据出错啦！");
                    // this.$message.error(res.msg);
                }
            }).catch(error => {
                // this.$message.error(error.msg);
            })
            localStorage.removeItem("singerSongList");
        },
        // 向后台请求某歌手的歌单数据
        getSingerSongList(context, id) {
            localStorage.removeItem('songList');
            request.post('/singer/singerAllSongs', null, {
                params: {
                    id: id
                }
            }).then(res => {
                if (res.code === '200') {
                    context.commit('SETLOCALSONGLIST', res.data);
                    // this.$message.success('');
                } else {
                    // this.$message.error(res.msg);
                }
            }).catch(error => {
                // this.$message.error(error.msg);
            })
        },
    },
    mutations: {
        GETLOCALSONGLIST(state) {
            state.songList = JSON.parse(localStorage.getItem('songList'));
        },
        SETLOCALSONGLIST(state, songList) {
            localStorage.setItem("songList", JSON.stringify(songList)); // 将歌曲存储于浏览器
            state.songList = JSON.parse(localStorage.getItem('songList'));
        },
    }
}