<template>
    <div>
        <!-- <p>singerdetail</p> -->
        <!-- <p>{{ singerId }}</p> -->
        <SongList :songList="songList"></SongList>
    </div>
</template>

<script>
import request from "@/util/request";
import SongList from '@/components/home/SongList';
export default {
    name: 'SingerDetail',
    components: { SongList },
    data() {
        return {
            singerId: '',
            songList: []
        }
    },
    created() {
        //接收PlayList传过来的参数
        this.singerId = this.$route.params.singerId
        let _this = this
        request.post('/singer/singerAllSongs', null, {
            params: {
                id: _this.singerId
            }
        }).then(res => {
            if (res.code === '200') {
                _this.songList = res.data;
                // this.$message.success('');
            } else {
                this.$message.error(res.msg);
            }
        }).catch(error => {
            this.$message.error(error.msg);
        })
    },

}
</script>

