<template>
  <div style="width: 100%;height: 15vh;min-height: 80px;display: flex;align-items: center;min-width: 1250px;justify-content: center">
    <div style="display: flex;align-items: center;justify-content: right;width: 20%;min-width: 250px">
      <img src="@/assets/img/logo.png" style="width: 60px;height: 60px">
      <span style="color:#1296db;font-size: 35px;font-family: Kaiti">YYC电影网</span>
    </div>
    <div style="width: 5%;min-width: 200px;display:flex;justify-content: center">
      <el-button type="text" class="about" style="color: gray;font-size: 20px">
        <span style="display: list-item" >关于我们</span>
      </el-button>
    </div>
    <div style="display: flex;align-items: center;justify-content: left;min-width: 650px;width: 900px">
      <div >
        <img src="@/assets/img/contact.png" style="width: 50px;height: 50px">
      </div>
      <div style="min-width: 190px;padding-left: 10px">
        <div style="height: 15px;font-size: 15px;color: gray">联系电话</div>
        <div style="height: 35px;font-size: 25px">0756-5565256</div>
      </div>
      <div><img src="@/assets/img/line.png" style="height: 50px;width: 5px"></div>
      <div style="min-width: 265px;padding-left: 10px">
        <div style="height: 15px;font-size: 15px;color: gray">邮箱</div>
        <div style="height: 35px;font-size: 25px">821720527@qq.com</div>
      </div>
      <div><img src="@/assets/img/line.png" style="height: 50px;width: 5px"></div>
      <div style="width: 150px;padding-left: 10px">
        <div style="height: 15px;font-size: 15px;color: gray">QQ</div>
        <div style="height: 35px;font-size: 25px">821720527</div>
      </div>
      <div>
        <img src="@/assets/img/wechat.jpg" style="width: 80px;height: 80px">
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "footer",
  components:{

  },
  data(){
    return{
    }
  }

}
</script>

<style scoped>

</style>