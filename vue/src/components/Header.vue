<template>
  <div style="height: 10vh; line-height: 50px; border-bottom: 1px solid #ccc; display: flex; width: 100%">
    <div style="width: 30%; padding-left: 30px; font-weight: bold; color: blue;font-size: 40px;padding-top: 8px">后台管理系统</div>
    <div style="width: 70%"></div>
    <div style="width: 20%; display: flex;align-items: center;justify-content: right;padding-right: 20px" >
      <el-row class="demo-avatar demo-basic">
        <el-col >
          <div class="demo-basic--circle" >
            <div class="block" style="display: flex;align-items: center">
              <el-avatar :size="40" :src=this.loginImg />
            </div>
          </div>
        </el-col>
      </el-row>
      <el-dropdown style="padding-left: 10px">
        <span class="el-dropdown-link" style="font-size: 25px;display:flex;justify-content: right">
          <span>{{this.loginUsername}}</span>
          <el-icon class="el-icon--right" style="font-size: 20px;display: flex;justify-content: left">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="renew">个人信息</el-dropdown-item>
            <el-dropdown-item>修改密码</el-dropdown-item>
            <el-dropdown-item @click="logout">登出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    <div>
      <el-dialog v-model="renewDialog"  width="500px" center>
        <el-form :model="form" ref="renewForm" label-width="auto" size="middle" class="managerForm" :rules="rules" >
          <el-form-item label="编号" prop="id">
            <p v-text="form.id" style="padding-left: 1vh"></p>
          </el-form-item>
          <el-form-item label="头像" >
            <el-upload
                class="avatar-uploader"
                action="http://localhost:81/files/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload"
            >
              <img v-if="form.img" :src="form.img" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
          </el-form-item>
          <el-form-item label="用户名" prop="username">
            <el-input  v-model="form.username"/>
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
            <p v-text="form.ascription" style="padding-left: 1vh"></p>
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
        <el-button @click="reback">还原</el-button>
        <el-button type="primary" @click="saveReManager">保存</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import { ArrowDown, UserFilled ,Plus } from '@element-plus/icons-vue'
  import {ElMessage} from "element-plus";
  import request from "@/utils/request";
  import dayjs from "dayjs";
  export default {
    name: "Header.vue",
    components:{
      ArrowDown,
      UserFilled,
      Plus
    },
    created() {
      this.load()
    },
    data(){
      return{
        form:{},
        renewDialog: false,
        loginUsername:"",
        loginImg:"",
        loginUser: sessionStorage.getItem("loginUser") ? JSON.parse(sessionStorage.getItem("loginUser")) : "",
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
      renew(){
        this.renewDialog=true
      },
      load(){
        console.log(this.loginUser)
        console.log("/managers/getOneManager/" + this.loginUser)
        request.get("/managers/getOneManager/" + this.loginUser).then(res => {
          console.log(res)
          this.form = res.data
          this.form.id = this.preFixInteger(this.form.id)
          this.loginUsername = res.data.username
          this.loginImg = res.data.img
        })
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
      },
      handleAvatarSuccess(res){
        console.log(res)
        this.form.img = res
        console.log(this.form.img)
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
        const isLt1M = file.size / 1024 / 1024 < 1;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG或PNG 格式!');
        }
        if (!isLt1M) {
          this.$message.error('上传头像图片大小不能超过 1MB!');
        }
        return isJPG && isLt1M;
      },
      saveReManager(){
        this.$refs.renewForm.validate().then(()=>{
          this.form.birthday = dayjs(new Date(this.form.birthday)).format(' YYYY-MM-DD HH:mm:ss').toString()
          request.put("/managers/updateManager",this.form).then(res => {
            console.log(res)
            this.reManagerDialog = false
            if(res.code == '200'){
              ElMessage({
                showClose: true,
                type: "success",
                message: "修改数据成功！",
                center: true,
              })
              this.load()
            }
            else {
              ElMessage({
                showClose: true,
                type: "error",
                message: "修改数据失败！",
                center: true,
              })
            }
          })
        }).catch(() => {
          ElMessage({
            type: 'error',
            message: '所填数据有误，请重新确认！',
            showClose: true,
          })
        })
      },
      reback(){
        console.log("in")
        console.log(this.remain)
        this.load()
      },
      // id填充0
      preFixInteger(num) {
        console.log((Array(6).join('0') + num).slice(-6))
        return (Array(6).join('0') + num).slice(-6)
      },
    }

  }


</script>

<style scoped>

/*上传头像*/
.avatar-uploader .avatar {
  width: 80px;
  height: 80px;
  display: block;
}
</style>
  <style>
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


</style>