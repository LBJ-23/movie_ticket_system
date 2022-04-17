<template>
  <div :style="bg" style="align-items: center;justify-content: center;display: flex">
    <div style="width: 25%;" >
      <div style="text-align: center;height: 5vh;font-size: 25px">电影管理后台登录系统</div>
      <div style="height: 15vh;display: flex;align-items: center;width: 100%;justify-content: center">
        <el-select v-model="type" class="m-2" placeholder="Select" size="large" style=";width: 30%">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </div>
      <el-form :model="loginForm" width="120px" style="text-align: center" size="large">
        <el-form-item >
          <el-input v-model="loginForm.name" id="uname" :placeholder="userNamePlaceholder"/>
        </el-form-item>
        <el-form-item>
          <el-input v-model="loginForm.password" show-password/>
        </el-form-item>
      </el-form>
      <div style="display: flex;align-items: flex-start">
        <div style="width: 50%">
          <el-checkbox>记住密码</el-checkbox>
        </div>
        <div style="width: 50%;display: flex;justify-content: right">
          <el-button type="text">忘记密码</el-button>
        </div>
      </div>
      <div style="display: flex;justify-content: center">
        <el-button type="primary" @click="login" style="width: 100%" size="large">登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data(){
    return{
      loginForm: {},
      type: 'id' ,

      options:[
        {
          value: 'id',
          label: '编号'
        },
        {
          value: 'username',
          label: '用户名'
        },
        {
          value: 'phone',
          label: '电话号码'
        },
      ],
      bg:{
        backgroundImage: "url(" + require("@/assets/img/login_admin.jpg") + ")",
        backgroundSize: "100% 100%",
        backgroundRepeat: "no-repeat",
        height: "100vh",
        width: "100%",
      },
    }
  },

  methods:{
    login(){

    },

  },
  computed:{
    // 改变username输入框的placehoder值
    userNamePlaceholder(){
      if(this.type === "id"){
        return "请输入编号"
      }
      else if(this.type === "username"){
        return "请输入用户名"
      }
      else if(this.type === "phone"){
        return "请输入电话号码"
      }
    }
  }
}

</script>

<style scoped>
.el-input{
  border: 1px groove;
  border-radius: 5px;
}
</style>