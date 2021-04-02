<template>
  <div style="width: 500px; text-align: center; border: 3px solid #505458;">
<!--    <el-button class="add-button" type="success" @click="dialogFormVisible = true">创建用户</el-button>-->
      <el-form :model="userForm" :rules="rules">
        <h3>创建账号</h3>
        <el-form-item prop="username">
          账号：
          <el-input type="text" v-model="userForm.username"
                    auto-complete="off" style="width: 70%"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          密码：
          <el-input type="text" v-model="userForm.password"
                    auto-complete="off" style="width: 70%"></el-input>
        </el-form-item>
        <el-form-item style="width: 100%">
          <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="createUser">添加
          </el-button>
        </el-form-item>
      </el-form>
  </div>
</template>

<script>
export default {
  name: 'CreateUser',
  data() {
    return {
      dialogFormVisible: false,
      rules: {
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      userForm: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    clear() {
      this.userForm = {
        username: '',
        password: ''
      }
    },
    createUser() {
      this.$axios
          .post('/gm/createUser', {
            username: this.userForm.username,
            password: this.userForm.password
          })
          .then(resp => {
            if (resp.data.success) {
              this.$alert('账号创建成功', '提示', {
                confirmButtonText: '确定'
              })
              this.clear()
              this.$emit('onSubmit')
            } else {
              this.$alert(resp.data.msg, '提示', {
                confirmButtonText: '确定'
              })
            }
          })
    }
  }
}
</script>
