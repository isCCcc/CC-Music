<template>
    <div style="margin-top:20px">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="账号" prop="name">
                <el-input v-model="ruleForm.name" style="width:30%"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pwd">
                <el-input type="password" v-model="ruleForm.pwd" style="width:30%" @focus="focus" @blur="blur"
                    ref="per_pwd"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
                <el-radio-group v-model="ruleForm.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="ruleForm.email" style="width:30%"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
                <el-input v-model="ruleForm.phone" style="width:30%"></el-input>
            </el-form-item>
            <el-form-item label="简介" prop="info">
                <el-input type="textarea" v-model="ruleForm.info" style="width:30%"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>

<script>
import request from '@/util/request';
export default {
    inject: ['reload'],
    name: 'PerInfo',
    data() {
        return {
            ruleForm: {
                id: JSON.parse(localStorage.getItem('user')).uid,
                name: JSON.parse(localStorage.getItem('user')).uname,
                pwd: JSON.parse(localStorage.getItem('user')).upassword,
                sex: JSON.parse(localStorage.getItem('user')).usex,
                email: JSON.parse(localStorage.getItem('user')).uemail,
                phone: JSON.parse(localStorage.getItem('user')).uphone,
                info: JSON.parse(localStorage.getItem('user')).uinfo,
            },
            rules: {
                name: [
                    { required: true, message: '请输入新账号', trigger: 'blur' },
                    { min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur' }
                ],
                pwd: [
                    { required: true, message: '请输入新密码', trigger: 'blur' },
                    { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                sex: [
                    { required: true, message: '请勾选性别', trigger: 'change' }
                ],
                desc: [
                    { required: false, message: '请填写简介', trigger: 'blur' }
                ]
            },
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                request.post('/user/edit', null, {
                    params: {
                        uid: this.ruleForm.id,
                        uname: this.ruleForm.name,
                        upassword: this.ruleForm.pwd,
                        uemail: this.ruleForm.email,
                        usex: this.ruleForm.sex,
                        uphone: this.ruleForm.phone,
                        uinfo: this.ruleForm.info,
                    }
                }).then(res => {
                    if (res.code === '200') {
                        console.log(res)
                        localStorage.setItem("user", JSON.stringify(res.data)); // 更新浏览器的数据
                        this.$message.success('修改成功，返回首页');
                        this.$router.push('/home')
                    } else {
                        this.$message.error(res.msg)
                    }
                }).catch(error => {
                    this.$message.error(error.msg)
                })
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        // 失去焦点事件
        blur() {
            this.$refs.per_pwd.type = 'password'
        },
        // 获得焦点事件
        focus() {
            this.$refs.per_pwd.type = "text"
        },
    }
}
</script>
<style scoped>
i {
    position: absolute;
    left: 2px;
    top: 2px;
    width: 24px;
    height: 24px;
}
</style>
