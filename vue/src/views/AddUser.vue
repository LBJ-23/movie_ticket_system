<template>
  <div style="min-width: calc(100vh - 200px);min-height: 90vh;display:flex;align-items: center;justify-content: center">
    <el-form :model="userForm" ref="userForm" label-width="120px" size="large" class="userForm" :rules="rules" >
      <el-form-item label="头像" >
        <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="userForm.username" style="width: 500px"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userForm.password" type="password" show-password/>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="userForm.sex"  >
          <el-radio style="" label="男" />
          <el-radio label="女" />
          <el-radio label="保密" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期" prop="birthday">
        <el-col :span="11">
          <el-date-picker
              v-model="userForm.birthday"
              type="date"
              placeholder="请选择出生年月日"
              style="width: 100%"
          />
        </el-col>
      </el-form-item>

      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="userForm.phone" />
      </el-form-item>
      <!--      <el-form-item label="Instant delivery">-->
      <!--        <el-switch v-model="form.delivery" />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="Activity type">-->
      <!--        <el-checkbox-group v-model="form.type">-->
      <!--          <el-checkbox label="Online activities" name="type" />-->
      <!--          <el-checkbox label="Promotion activities" name="type" />-->
      <!--          <el-checkbox label="Offline activities" name="type" />-->
      <!--          <el-checkbox label="Simple brand exposure" name="type" />-->
      <!--        </el-checkbox-group>-->
      <!--      </el-form-item>-->

      <el-form-item label="地址" prop="address">
        <el-input v-model="userForm.address" type="textarea" style="border: outset"/>
      </el-form-item>
      <el-form-item style="padding-left: calc(50vh - 250px)">
        <el-button @click="this.$refs.userForm.resetFields()" >重新输入</el-button>
        <el-button type="primary" @click="save" >保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
import {ElMessage} from "element-plus";
import { Plus } from '@element-plus/icons-vue'
import dayjs from "dayjs";
import request from "@/utils/request";

export default {
  name: "addUser",
  components:{
    ElMessage,
    Plus,
  },
  data(){
    return{
      userForm:{},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {max: 20, message: '用户名长度不能超过20个字符', trigger: 'change'},
        ],
        password: [
          {required: true, message: '请输入密码',trigger: 'blur'},
          {pattern:/^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{8,18}$/, message: '密码必须包含大小写字母和数字，且长度为8-18位' ,trigger:'change'}
        ],
        sex: [{required: true, message: '请选择性别', trigger: 'change'}],
        birthday: [{required: true, message: '请选择日期', trigger: 'change'}],
        phone: [
          {required:true, message: '请输入电话号码', trigger: 'blur'},
          {pattern:/^1\d{10}$/, message: '电话号码格式错误', trigger: 'change'}
        ],
        address: [
          {required: true, message: '请输入地址', trigger: 'blur'},
          {max: 50 ,message: '地址长度不能超过50个字符',trigger: 'change'}
        ]
      },
    }
  },
  methods:{
    save(){
      // this.$refs.managerForm.validate().then(()=>{}).catch(()=>{})
      this.$refs.userForm.validate().then(() => {
        this.userForm.birthday = dayjs(new Date(this.userForm.birthday)).format(' YYYY-MM-DD HH:mm:ss').toString()
        console.log(this.form.birthday)
        console.log("right")
        request.post("/users/saveUser", this.form).then(res => {
          console.log(res)
          if(res.code === '200'){
            ElMessage({
              showClose: true,
              type: "success",
              message: "添加该名用户信息成功！",
              center: true,
            })
          }
          else {
            ElMessage({
              showClose: true,
              type: "error",
              message: "添加该名用户信息失败！",
              center: true,
            })
          }
          this.$refs.userForm.resetFields()
        })
      }).catch(() => {
        console.log("wrong")
        ElMessage({
          showClose: true,
          type: "error",
          message: "所填信息有误，请重新确认！",
          center: true
        })
      })
    },
  }

}
</script>

<style scoped>

/*上传头像*/
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.avatar-uploader .el-upload {
  border: 3px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 80px;
  height: 80px;
  text-align: center;
}
.el-form-item__label {
  font-size: 20px;
}
.el-radio__input{
  border-radius: 50%;
  border: 1px ridge white;
}
</style>