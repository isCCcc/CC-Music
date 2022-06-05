<template>
    <div class="container">
        <div class="play-list-out">
            <div class="play-list">
                <ul class="play-body">
                    <li class="card-frame" v-for="(item, index) in singerList" :key="index">
                        <div class="card" @click="goAblum(item.id)">
                            <el-image class="card-img" fit="contain" :src="attachImageUrl(item.pic)" />
                            <div class="mask" @click="goAblum(item.id)">
                                <div class="mask-icon"></div>
                            </div>
                        </div>
                        <p class="card-name">{{ item.name }}</p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    inject: ['reload'],
    name: 'PlayList',
    data() {
        return {
            singerList: JSON.parse(localStorage.getItem('singerList')),
        }
    },
    // props: {
    // singerList: {
    //     type: Array,
    //     default: function () {
    //         return []
    //     }
    // }
    // },
    methods: {
        attachImageUrl(url) {
            return require("@/assets" + url)
        },
        goAblum(id) {
            //利用$router.push进行跳转        
            this.$router.push({
                //path后面跟跳转的路由地址
                path: '/singerDetail',
                //name后面跟跳转的路由名字（必须有亲测，不使用命名路由会传参失败）
                name: 'singerDetail',
                params: {
                    //singerList是自己定义的名字，id是要被传递的值
                    singerId: id
                }
            })
        }
    },

}
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

* {
    margin: 0;
    padding: 0;
}

.container {
    // width: 100vh;
    width: 100vw;
    height: 100%;
    background: linear-gradient(200deg, #e2e2e5, #e2e2e0);
}

.container::before {
    content: "";
    display: table;
}

.play-list-out {
    width: 80%;
    margin-top: 20px;
}



.play-list {
    padding: 0 1rem;
    width: 80%;
    display: flex;
    float: right;
    right: 20%;
    top: 20px;
    border-radius: 30px;
    background: rgba(255, 255, 255, .3);
    box-shadow: 0 12px 45px #b7b6b6;

    .play-title {
        height: 60px;
        line-height: 60px;
        font-size: 28px;
        font-weight: 500;
        text-align: center;
        color: $color-black;
        box-sizing: border-box;
    }

    .play-body {
        @include layout(flex-start, stretch, row, wrap);
    }
}

.card-frame {
    list-style: none;
    display: inline-block;

    .card {
        position: relative;
        height: 0;
        padding-bottom: 100%;
        overflow: hidden;
        border-radius: 5px;

        .card-img {
            width: 100%;
            transition: all 0.4s ease;
        }
    }

    .card-name {
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        margin: 0.5rem 0;
    }

    &:hover .card-img {
        transform: scale(1.2);
    }
}

.mask {
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
    border-radius: 5px;
    background-color: rgba(52, 47, 41, 0.4);
    @include layout(center, center);
    transition: all 0.3s ease-in-out;
    opacity: 0;

    .mask-icon {
        @include icon(2em, rgba(240, 240, 240, 1));
    }

    &:hover {
        opacity: 1;
        cursor: pointer;
    }
}

@media screen and (min-width: $sm) {
    .card-frame {
        width: 18%;
        margin: 0.5rem 1%;
    }
}

@media screen and (max-width: $sm) {
    .card-frame {
        width: 46%;
        margin: 0.5rem 2%;
    }
}
</style>