
export default {
    namespaced: true,
    state: {
        isPlay: false, // 歌曲播放情况
        songIndex: '',  // 播放歌曲的下标
        progressT: '', //鼠标在进度条上悬停的位置
        progressLoc: '', //鼠标在进度条上悬停的音频位置
        cm: '', //悬停音频位置(分钟)
        ctMinutes: '', //悬停播放位置(分)
        ctSeconds: '', //悬停播放位置(秒)
        curMinutes: '', //当前播放时间(分)
        curSeconds: '', //当前播放时间(秒)
        durMinutes: '', //音频总时长(分)
        durSeconds: '', //音频总时长(秒)
        playProgress: '', //播放进度
        songList: JSON.parse(localStorage.getItem('songList')),
    },
    mutations: {
        SETPROGRESST: (state, progressT) => { state.progressT = progressT },
        SETPROGRESSLOC: (state, progressLoc) => { state.progressLoc = progressLoc; },
        SETCM: (state, cm) => { state.cm = cm; },
        SETCTMINUTES: (state, ctMinutes) => { state.ctMinutes = ctMinutes; },
        SETCTSECONDS: (state, ctSeconds) => { state.ctSeconds = ctSeconds; },
        SETCURMINUTES: (state, curMinutes) => { state.curMinutes = curMinutes; },
        SETCURSECONDS: (state, curSeconds) => { state.curSeconds = curSeconds; },
        SETDURMINUTES: (state, durMinutes) => { state.durMinutes = durMinutes; },
        SETDURSECONDS: (state, durSeconds) => { state.durSeconds = durSeconds; },
        SETPLAYPROGRESS: (state, playProgress) => { state.playProgress = playProgress; },
        PLAYERINDEX: (state, index) => { state.songIndex = index; },
        ISPLAY: (state, isplay) => { state.isPlay = isplay; },
        NEXTINDEX: (state, nextIndex) => { state.songIndex = nextIndex; }
    }
}