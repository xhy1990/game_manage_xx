<template>
  <el-card class="admin-header">
    <a href="/index">
      <img src="../../assets/logo.png" alt="" width="30px" style="float: left;">
    </a>
    <span style="font-weight: bold;position:absolute;left: 100px">GM管理后台</span>
    <i class="el-icon-switch-button" v-on:click="logout" style="float: right"></i>
  </el-card>
</template>

<script>
// import {createRouter} from '@/router'

export default {
  name: 'Header',
  methods: {
    logout() {
      this.$http.post('/gm/logout')
          .then(resp => {
            if (resp.data.success) {
              this.$store.commit('logout')
              this.$router.replace('/login')
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

<style scoped>
.admin-header {
  height: 60px;
  opacity: 0.85;
  /*line-height: 40px;*/
  /*min-width: 900px;*/
}

.el-icon-switch-button {
  cursor: pointer;
  outline: 0;
}
</style>
