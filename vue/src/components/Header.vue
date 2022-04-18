<template>
  <div style="height: 10vh; line-height: 50px; border-bottom: 1px solid #ccc; display: flex; width: 100%">
    <div style="width: 30%; padding-left: 30px; font-weight: bold; color: blue;font-size: 40px;padding-top: 8px">后台管理系统</div>
    <div style="width: 70%"></div>
    <div style="width: 20%; display: flex;align-items: center;justify-content: right;padding-right: 20px" >
      <el-row class="demo-avatar demo-basic">
        <el-col >
          <div class="demo-basic--circle" >
            <div class="block" style="display: flex;align-items: center">
              <el-avatar :size="30" :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
            </div>
          </div>
        </el-col>
      </el-row>
      <el-dropdown style="padding-left: 20px">
        <span class="el-dropdown-link" style="font-size: 25px;display:flex;justify-content: right">
          username
          <el-icon class="el-icon--right" style="font-size: 20px;display: flex;justify-content: left">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">登出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
  import { ArrowDown, UserFilled , } from '@element-plus/icons-vue'
  export default {
    name: "Header.vue",
    components:{
      ArrowDown,
      UserFilled
    }
  }
  //头像


</script>

<style scoped>

  /*头像*/

</style>