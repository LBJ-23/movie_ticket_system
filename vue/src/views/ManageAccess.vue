<template>
  <div>
    这是管理管理员权限界面
    <div class="demo-pagination-block" style="display: flex;justify-content: center;padding-top: 20px">
      <el-pagination
          v-model:currentPage="currentPage"
          layout="total, prev, pager, next, jumper"
          :total="total"
          @current-change="handleCurrentChange"
          class="page"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "manageAccess",
  data(){
    return{
      total: 10,
      pageSize: 5,
      CurrentPage: 1,
    }

  }
}
</script>

<style scoped>

</style>