<template>
  <div style="flex: 1;display: flex;align-items: center;justify-content: center">
    <el-menu
        :default-active="'movie' + this.$route.path"
        class="el-menu-demo"
        mode="horizontal"
        style="width: 100%;height: 100px"
        router

    >
      <el-menu-item index="movie/nowMovie" style="font-size: 40px">正在热映</el-menu-item>
      <el-menu-item index="movie/soonMovie" style="font-size: 40px">即将上映</el-menu-item>

    </el-menu>
  </div>
  <div>
    <router-view/>
  </div>
</template>
<script>
export default {
  name: "MovieHeader"
}
</script>

<style scoped>

</style>