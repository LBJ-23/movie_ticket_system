<template>
  <div style="display: flex;height: 15vh;min-width:1250px;width: 100%;border-bottom: 5px inset; padding-left: 5%;min-height: 50px">
    <div style="display:flex;width:200px;min-width: 190px;text-align: center;align-items: center;justify-content: center;background-color: #1296db">
      <span style="font-size: 38px;color: white;font-family: STHupo">YYC电影网</span>
    </div>
    <div style="flex: 1;display: flex;align-items: end;justify-content: center">
      <el-menu
          :default-active="this.$route.name"
          class="el-menu-demo"
          mode="horizontal"
          style="width: 900px"
          router
      >
        <el-menu-item index="index" >首页</el-menu-item>
        <el-menu-item index="movie" >电影</el-menu-item>
        <el-menu-item index="cinema" >电影院</el-menu-item>
        <el-menu-item index="news" >电影资讯</el-menu-item>
      </el-menu>
    </div>
    <div style="width: 190px;display: flex;height:15vh;align-items: center">
      <el-row class="demo-avatar demo-basic">
        <el-col >
          <div class="demo-basic--circle" >
            <div class="block" style="display: flex;align-items: center">
              <el-avatar :size="40" :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
            </div>
          </div>
        </el-col>
      </el-row>
      <el-dropdown style="padding-left: 5px">
        <span class="el-dropdown-link" style="font-size: 25px;display:flex;justify-content: right">
          username
          <el-icon class="el-icon--right" style="font-size: 25px;display: flex;justify-content: left">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item>登出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import {ArrowDown} from '@element-plus/icons-vue'
export default {
  name: 'Header',
  components:{
    ArrowDown,
  },
  methods:{

  },
  created() {
    console.log()

  }
}


</script>

<style scoped>
.el-menu-item{
  font-size: 30px;
  width: 200px;
}
</style>
