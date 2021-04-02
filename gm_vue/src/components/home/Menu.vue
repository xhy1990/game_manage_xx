<template>
  <el-menu
      :default-active="$router.currentRoute.path"
      class="el-menu-admin"
      router
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      :collapse="isCollapse">
    <!--      @select="onMenuSelected">-->
    <!--index 没有用但是必需字段-->
    <el-submenu v-for="(item,i) in menuList" :key="i" :index="(i).toString()" style="text-align: left">
        <span slot="title" style="font-size: 17px;">
<!--          <i :class="el-icon-user"></i>-->
          {{ item.name }}
        </span>
      <el-menu-item v-for="(child,i) in item.childList" :key="i" :index="child.path">
        <!--          <i :class="el-icon-user"></i>-->
        {{ child.name }}
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: 'Menu',
  data() {
    return {
      isCollapse: false,
      menuList: [],
      // routes: []
    }
  },
  mounted() {
    this.getMenu()
  },
  methods: {
    // getMenu: function () { // 从后台获取菜单列表
    //   this.$http.get('/gm/menu').then(res => {
    //     if (res.data.success) {
    //       this.menuList = res.data.data
    //       this.initRoutes()
    //     } else {
    //       this.$store.commit('logout')
    //       this.$router.replace('/login')
    //       // this.$message.error(res.data.data)
    //     }
    //   }).catch(error => {
    //     this.$message.error(error)
    //   })
    // },
    // initRoutes() {
    //   this.menusToRoutes(this.menuList)
    //   this.$store.commit('initMenu', this.routes)
    // },
    // menusToRoutes(menus) {
    //   menus.forEach(menu => {
    //     if (menu.childList) {
    //       this.menusToRoutes(menu.childList)
    //     }
    //     if (!menu.path) {
    //       return
    //     }
    //     console.log(JSON.stringify(menu))
    //     let route = {
    //       path: menu.path,
    //       // name: menu.routeName,
    //       // component: resolve => {
    //       //   require(['@/components/' + menu.component + '.vue'], resolve)
    //       // },
    //       component: () => import('@/components/' + menu.component),
    //       meta: {
    //         requireAuth: true
    //       }
    //     }
    //     this.$router.addRoute('home', route)
    //     this.routes.push(route)
    //   })
    // }

    getMenu: function () { // 从后台获取菜单列表
      this.menuList = this.$store.getters.menus
    },
  }
}
</script>

<style scoped>
.el-menu-admin {
  border-radius: 5px;
  height: 100%;
}
</style>
