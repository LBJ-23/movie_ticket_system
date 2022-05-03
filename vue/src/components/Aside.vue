<template>
  <div>
    <el-menu

        style="width: 200px;min-height: 90vh;"
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        default-active="index"
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
        router
    >
      <el-menu-item index="index" >
        <el-icon><location/></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-sub-menu index="uer">
        <template #title>
          <el-icon><icon-menu /></el-icon>
          <span>用户管理</span>
        </template>
          <el-menu-item index="addUser">添加用户</el-menu-item>
          <el-menu-item index="manageUsers">管理信息</el-menu-item>
          <el-menu-item index="account">账户</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="manager">
        <template #title>
          <el-icon><icon-menu /></el-icon>
          <span>管理员管理</span>
        </template>
        <el-menu-item index="addManager">添加管理员</el-menu-item>
        <el-menu-item index="manageManager">管理信息</el-menu-item>
        <el-menu-item index="manageAccess">权限管理</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="movie">
        <template #title>
          <el-icon><icon-menu /></el-icon>
          <span>电影管理</span>
        </template>
        <el-menu-item index="addMovie">添加电影信息</el-menu-item>
        <el-menu-item index="manageMovie">管理电影信息</el-menu-item>
        <el-menu-item index="addSession">添加场次信息</el-menu-item>
        <el-menu-item index="manageSession">管理场次信息</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="cinema">
        <template #title>
          <el-icon><icon-menu /></el-icon>
          <span>影院管理</span>
        </template>
        <el-menu-item index="addCinema">添加影院信息</el-menu-item>
        <el-menu-item index="manageCinema">管理影院信息</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="5">
        <el-icon><setting /></el-icon>
        <span>订单管理</span>
      </el-menu-item>
      <el-menu-item index="6">
        <el-icon><setting /></el-icon>
        <span>留言管理</span>
      </el-menu-item>
      <el-sub-menu index="7">
        <template #title>
          <el-icon><setting /></el-icon>
          <span>基本信息管理</span>
        </template>
          <el-menu-item index="7-1">网站信息</el-menu-item>
          <el-menu-item index="7-2">轮播图</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script>
  import {Document,Setting,Location,Menu as IconMenu,Search} from "@element-plus/icons";
  import {handleError} from "vue";

  export default {
    name: "Aside",
    components:{
      Document,
      Setting,
      Location,
      IconMenu
    }
  }


</script>

<style scoped>
/*span{*/
/*  font-size: 25px;*/
/*}*/
</style>