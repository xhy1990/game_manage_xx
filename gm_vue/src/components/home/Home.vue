<template>
  <el-container>
    <el-header style="padding: 0">
      <Header style="width: 100%;"></Header>
    </el-header>
    <el-container>
      <el-aside style="width: 250px">
        <admin-menu></admin-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import AdminMenu from './Menu'
import Header from './Header'

export default {
  name: 'Home',
  components: {AdminMenu, Header},
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
</style>
