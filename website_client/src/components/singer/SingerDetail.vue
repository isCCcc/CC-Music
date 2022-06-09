<template>
    <div>
        <SongList></SongList>
    </div>
</template>

<script>
import SongList from '@/components/home/SongList';
export default {
    name: 'SingerDetail',
    components: { SongList },
    created() {
        // 获取指定歌手的全部歌曲
        this.$store.dispatch('song/getSingerSongList', this.$route.params.singerId)
    },

}
</script>

