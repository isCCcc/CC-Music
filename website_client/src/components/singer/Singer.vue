<template>
    <PlayList :singer-list="singerList"></PlayList>
</template>

<script>
import request from "@/util/request";
import PlayList from "@/components/PlayList"
export default {
    inject: ['reload'],
    components: { PlayList },
    name: 'Singer',
    data() {
        return {
            singerList: [],
        }
    },
    created() {
        let _this = this;
        request.get('/singer/').then(res => {
            if (res.code === '200') {
                _this.singerList = res.data
                // console.log(_this.singerList)
            } else {
                this.$message.error(res.msg)
            }
        }).catch(error => {
            this.$message.error(error.msg)
        })
    },
}
</script>

<style scoped>
.out-display {
    vertical-align: middle;
}

td,
th {
    text-align: center;
    display: table-cell;
    /* 设置居中 */
    vertical-align: middle;
}
</style>
