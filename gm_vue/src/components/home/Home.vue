<template>
  <el-container>
    <el-header style="padding: 0">
      <Header style="width: 100%;"></Header>
    </el-header>
    <el-container>
      <el-aside style="width: 250px">
        <Menu></Menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Menu from './Menu'
import Header from './Header'

export default {
  name: 'Home',
  components: {Menu, Header},
  data () {
    return {
      dialogVisible: false
    }
  },
  mounted () {
    // this.$router.replace('/admin/dashboard')
  }
}
</script>

<style scoped>
#admin-body {
  height: 95vh;
}

.el-menu{
  width: 250px;
  position: absolute;
}
.el-submenu__title{
  position: relative;
}
.el-card{
  color: rgb(255, 208, 75);
  background-color:#2b303b;
  border:0px solid;
  border-radius: 0px;
}

</style>
