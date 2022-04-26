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
          <span>{{loginUser}}</span>
          <el-icon class="el-icon--right" style="font-size: 20px;display: flex;justify-content: left">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="renew">个人信息</el-dropdown-item>
            <el-dropdown-item @click="logout">登出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    <div>
      <el-dialog v-model="renewDialog"  width="500px" center>
        <el-form :model="form" ref="renewForm" label-width="auto" size="middle" class="managerForm" :rules="rules">
          <el-form-item label="编号" prop="id">
            <p v-text="form.id" style="padding-left: 1vh"></p>
          </el-form-item>
          <el-form-item label="用户名" prop="username">
            <el-input  v-model="form.username"/>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" type="password" />
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="form.sex"  >
              <el-radio style="" label="男" />
              <el-radio label="女" />
              <el-radio label="保密" />
            </el-radio-group>
          </el-form-item>
          <el-form-item label="出生日期" prop="birthday">
            <el-col :span="11">
              <el-date-picker
                  v-model="form.birthday"
                  type="date"
                  placeholder="请选择出生年月日"
                  style="width: 100%"
              />
            </el-col>
          </el-form-item>
          <el-form-item label="归属" prop="ascription">
            <el-select v-model="form.ascription" placeholder="请选择归属地方">
              <el-option label="诚丰电影院" value="诚丰电影院" />
              <el-option label="银河电影院" value="银河电影院" />
            </el-select>
          </el-form-item>
          <el-form-item label="电话号码" prop="phone">
            <el-input v-model="form.phone" />
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="form.address" type="textarea" style="border: outset"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="reManager(this.reback)">还原</el-button>
        <el-button type="primary" @click="saveReManager">保存</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import { ArrowDown, UserFilled , } from '@element-plus/icons-vue'
  import {ElMessage} from "element-plus";
  export default {
    name: "Header.vue",
    components:{
      ArrowDown,
      UserFilled
    },
    data(){
      return{
        form:{},
        renewDialog: false,
        loginUser: sessionStorage.getItem("loginUser") ? JSON.parse(sessionStorage.getItem("loginUser")) : ""
      }
    },
    methods:{
      renew(){
        this.renewDialog=true
        
      },
      logout(){
        this.$router.push("/login")
        sessionStorage.removeItem("loginUser")
        ElMessage({
          type: 'success',
          message: '登出成功！',
          showClose: true,
          center: true,
        })
      }
    }

  }


</script>

<style scoped>

  /*头像*/

</style>