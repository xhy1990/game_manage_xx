<template>
  <body id="paper">
  <el-form :model="loginForm" :rules="rules" class="login-container" v-loading="loading">
    <h3 class="login_title">GM系统登录</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="loginForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <!--    <el-checkbox class="login_remember" v-model="checked"-->
    <!--                 label-position="left"><span style="color: #505458">记住密码</span></el-checkbox>-->
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #4e6ef2;border: none" v-on:click="login">登录</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>
import store from "@/store";

export default {
  data() {
    return {
      rules: {
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        username: '',
        password: ''
      },
      loading: false,
      routes: []
    }
  },
  methods: {
    login() {
      // var _this = this
      this.$http.post('/gm/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(resp => {
          if (resp.data.success) {
            this.$store.commit('token', resp.data.data);

            this.$http.get('/gm/menu').then(res => {
              if (res.data.success) {
                this.$store.commit('initMenu', res.data.data)
                // this.initRoutes()
                this.$router.push({path: '/home'})
              }
            }).catch(error => {
              this.$message.error(error)
            })
          } else {
            this.$alert(resp.data.msg, '提示', {
              confirmButtonText: '确定'
            })
          }
        })
    },
    initRoutes() {
      // this.routes = this.$store.getters.routes
      // if(!this.routes || this.routes.length <= 0){
      //
      // }
      this.routes = []
      this.menusToRoutes(store.getters.menus)
      this.$store.commit('initRoute', this.routes)
    },
    menusToRoutes(menus) {
      menus.forEach(menu => {
        if (menu.childList) {
          this.menusToRoutes(menu.childList)
        }
        if (!menu.component) {
          return
        }
        console.log(JSON.stringify(menu))
        let route = {
          path: menu.path,
          component: () => import('@/components/' + menu.component),
          meta: {
            requireAuth: true
          }
        }
        this.$router.addRoute('home', route)
        this.routes.push(route)
      })
    }
  }
}
</script>
<style>
#paper {
  background: url("../assets/bg.jpg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 120px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 3px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

</style>
