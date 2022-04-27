<template>
  <div :style="bg" style="align-items: center;justify-content: center;display: flex">
    <div style="width: 25%;min-width: 300px" >
      <div style="text-align: center;height: 5vh;font-size: 25px">电影管理后台登录系统</div>
      <div style="height: 15vh;display: flex;align-items: center;width: 100%;justify-content: center">
        <el-select v-model="type" class="m-2" placeholder="Select" size="large" style="width: 30%;min-width: 120px">
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
          <el-input v-model="loginForm.username" id="uname" :placeholder="userNamePlaceholder"/>
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
          <el-button type="text" @click="findPassword">忘记密码</el-button>
        </div>
      </div>
      <div style="display: flex;justify-content: center">
        <el-button type="primary" @click="login" style="width: 100%" size="large">登录</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import {ElMessage, elPaginationKey} from "element-plus";

export default {
  name: "login",
  data(){
    return{
      loginForm: {},
      type: 'id' ,
      msgType: "账号",

      options:[
        {
          value: 'id',
          label: '账号'
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
    // 用户登录
    login(){
      request.post("/managers/login",this.loginForm,{
        params:{
          type: this.type,
        }
      }).then(res => {
        console.log(res)
        if(res.code === '0'){
          const msgT = this.msgType
          ElMessage({
            type: 'error',
            message: msgT + '错误或密码错误，请重新输入！',
            showClose: true,
          })
        }
        else if(res.code === '200'){
          console.log(res.data.username)
          sessionStorage.setItem("loginUser",JSON.stringify(res.data.id))
          this.$router.push('/')
        }
      })
    },
    findPassword(){

    },

  },
  computed:{
    // 改变username输入框的placehoder值
    userNamePlaceholder(){
      if(this.type === "id"){
        this.msgType = "账号"
        return "请输入账号"
      }
      else if(this.type === "username"){
        this.msgType = "用户名"
        return "请输入用户名"
      }
      else if(this.type === "phone"){
        this.msgType = "电话号码"
        return "请输入电话号码"
      }
    }
  }
}

</script>

<style scoped>
.el-input{
  border: 1px groove ;
  border-radius: 5px;
}
</style>