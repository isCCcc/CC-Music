<template>
    <div class="body">
        <div class="container">
            <!-- 注册登录的容器 -->
            <div class="form-box" ref="form_box">
                <!-- 登录 -->
                <div class="login-box" ref="login_box">
                    <h1>LOGIN</h1>
                    <input type="text" placeholder="用户名" v-model="uname">
                    <input type="password" placeholder="密码" v-model="upwd">
                    <button @click="login(uname, upwd)">登录</button>
                </div>
                <!-- 注册 -->
                <div class="register-box hidden" ref="register_box">
                    <h1>REGISTER</h1>
                    <input type="text" placeholder="用户名" v-model="regname">
                    <input type="email" placeholder="邮箱" v-model="regemail">
                    <input type="password" placeholder="密码" v-model="regpwd">
                    <input type="password" placeholder="确认密码" v-model="repwd" @blur="check(regpwd, repwd)">
                    <button @click="register(regname, regemail, regpwd, repwd)">注册</button>
                </div>
            </div>
            <!-- 登录的背景模块 -->
            <div class="con-box left">
                <h2><span>C - Music</span></h2>
                <p>快来领取你的专属<span>音乐</span>吧</p>
                <img src="@/assets/img/login/3.png" alt="">
                <p>已有账号</p>
                <button id="login" @click="goLogin">去登录</button>
            </div>
            <!-- 注册的背景模块 -->
            <div class="con-box right">
                <h2><span>C - Music</span></h2>
                <p>快来领取你的专属<span>音乐</span>吧</p>
                <img src="@/assets/img/login/3.png" alt="">
                <p>没有账号</p>
                <button id="register" @click="goRegister">去注册</button>
            </div>
        </div>
    </div>

</template>

<script>
import request from '@/util/request'
export default {
    name: 'Login',
    inject: ['reload'],
    data() {
        return {
            uname: '',
            upwd: '',
            regname: '',
            regemail: '',
            regpwd: '',
            repwd: '',
            checkpwd: '',
        }
    },
    methods: {
        // 登录滑动模块
        goLogin() {
            this.$refs.form_box.style.transform = 'translateX(0)';
            this.$refs.login_box.classList.remove('hidden');
            this.$refs.register_box.classList.add('hidden');
        },
        // 注册滑动模块
        goRegister() {
            this.$refs.form_box.style.transform = 'translateX(80%)';
            this.$refs.login_box.classList.add('hidden');
            this.$refs.register_box.classList.remove('hidden');
        },

        // 登录
        login(uname, upassword) {
            let _this = this
            if (uname == "" || upassword == "") {
                _this.$message.error("登录失败，请填写正确的账号及密码！");
            } else {
                axios.get('http://localhost:8081/user/login/' + uname + '/' + upassword).then(function (resp) {
                    console.log(resp)
                    if (resp.data.code == 200) {
                        localStorage.setItem("user", JSON.stringify(resp.data.data)); // 存储用户信息到浏览器
                        // 动态设置当前用户路由
                        _this.$router.push("/appHome");
                        _this.$message.success("登录成功");
                    } else {
                        _this.$message.error(resp.data.msg);
                    }
                })
                // request.get('/singer/').then(res => {
                //     if (res.code === '200') {
                //         _this.singerList = res.data
                //         // console.log(_this.singerList)
                //     } else {
                //         this.$message.error(res.msg)
                //     }
                // }).catch(error => {
                //     this.$message.error(error.msg)
                // })

                // request.post('/user/login', null, {
                //     params: {
                //         uname: uname,
                //         upassword: upassword,
                //     }
                // }).then(res => {
                //     if (res.code === '200') {
                //         localStorage.setItem("user", JSON.stringify(resp.data.data)); // 存储用户信息到浏览器
                //         // 动态设置当前用户路由
                //         _this.$router.push("/appHome");
                //         _this.$message.success("登录成功");
                //     } else {
                //         this.$message.error(res.msg);
                //     }
                // }).catch(error => {
                //     this.$message.error(error.msg);
                // })


            }
        },
        // 注册
        register(uname, uemail, upassword, repassword) {
            // regname, regemail, regpwd, repwd
            let _this = this
            if (_this.check(upassword, repassword)) {
                request.post('/user/register', null, {
                    params: {
                        uname: uname,
                        upassword: upassword,
                        uemail: uemail,
                    }
                }).then(res => {
                    if (res.code === '200') {
                        console.log(res)
                        // localStorage.setItem("user", JSON.stringify(res.data)); // 更新浏览器的数据
                        _this.$message.success('注册成功，返回登录');
                        // _this.$router.push('/')
                        _this.reload()
                    } else {
                        _this.$message.error(res.msg)
                    }
                }).catch(error => {
                    _this.$message.error(error.msg)
                })
            }
        },
        // 检验密码是否一致
        check(regpwd, repwd) {
            if (regpwd !== repwd) {
                this.$message.error("密码不一致！");
                return false;
            }
            return true;
        }


    },
}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
}

.body {
    /* !vh??? 100%窗口高度 */
    height: 100vh;
    /* !弹性布局 水平+垂直居中 */
    display: flex;
    justify-content: center;
    align-items: center;
    /* 渐变色背景 渐变线的角度为200° / 即从右上角往左下角渐变 */
    background: linear-gradient(200deg, #e2e2e5, #c2c2c0);
    /* background: linear-gradient(200deg, #69696b, black); */
}

.container {
    background-color: #fff;
    width: 650px;
    height: 415px;
    border-radius: 5px;
    box-shadow: 5px 5px 5px rgba(0, 0, 0, .1);
    position: relative;
}

.form-box {
    /* 绝对定位 */
    position: absolute;
    top: -10%;
    left: 5%;
    background-color: rgb(107, 109, 116);
    width: 320px;
    height: 500px;
    border-radius: 5px;
    box-shadow: 2px 0 10px rgba(0, 0, 0, .1);
    /* box-shadow: rgba(0, 0, 0, 0.2) 0px 0px 40px 16px; */
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 2;
    /* 过渡动画 加速后减速 */
    transition: 0.5s ease-in-out;
}

.login-box,
.register-box {
    /* 弹性布局 垂直排列 */
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
}

.hidden {
    display: none;
    transition: 0.5s;
}

h1 {
    text-align: center;
    margin-bottom: 25px;
    text-transform: uppercase;
    color: #fff;
    /* 字体间距 */
    letter-spacing: 5px;

}

input {
    background-color: transparent;
    width: 70%;
    color: #fff;
    border: none;
    border-bottom: 1px solid rgba(255, 255, 255, 0.4);
    /* 内边距 */
    padding: 10px 0;
    text-indent: 10px;
    /* 外边距 */
    margin: 8px 0;
    font-size: 14px;
    letter-spacing: 2px;
}

input::placeholder {
    color: #fff;
}

/* 鼠标放置的颜色？？？ */
input:focus {
    /* color: #a262ad; */
    color: #555;
    outline: none;
    border-bottom: 1px solid #2b2b2b;
    transition: .5s;
}

input:focus::placeholder {
    /* 设置元素透明背景：0-1 */
    opacity: 0;
}

.form-box button {
    width: 70%;
    margin-top: 35px;
    background-color: #f6f6f6;
    outline: none;
    border-radius: 8px;
    padding: 13px;
    color: #2b2b2b;
    letter-spacing: 2px;
    border: none;
    cursor: pointer;
}

.form-box button:hover {
    background-color: #333;
    color: #f6f6f6;
    transition: background-color 0.5s ease;
}

.con-box {
    width: 50%;
    /* 弹性布局 垂直排列 居中 */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
}

.con-box.left {
    left: -2%;
}

.con-box.right {
    right: -2%;
}

.con-box h2 {
    color: #8e9aaf;
    font-size: 25px;
    font-weight: bold;
    letter-spacing: 3px;
    text-align: center;
    margin-bottom: 4px;
}

.con-box p {
    color: #999;
    letter-spacing: 2px;
    font-size: 12px;
    text-align: center;
}

.con-box span {
    color: #706677;
}

.con-box img {
    width: 150px;
    height: 150px;
    opacity: 0.9;
    margin: 40px 0;
}

.con-box button {
    margin-top: 3%;
    background-color: #fff;
    color: #2b2b2b;
    border: 1px solid #e2e2e5;
    padding: 6px 10px;
    border-radius: 5px;
    letter-spacing: 1px;
    outline: none;
    cursor: pointer;
}

.con-box button:hover {
    /* background-color: #d3b7d8; */
    background-color: #e2e2e5;

    /* color: #fff; */
}
</style>
