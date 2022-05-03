<template>
  <div style="min-width: calc(100vh - 200px);min-height: 90vh;display:flex;align-items: center;justify-content: center">
    <el-form :model="cinemaForm" ref="cinemaForm" label-width="120px" size="large" class="cinemaForm" :rules="cinemaRules" >
      <el-form-item label="宣传图片" prop="img">
        <el-upload
            class="avatar-uploader"
            action="http://localhost:81/files/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
        >
          <img v-if="cinemaForm.img" :src="cinemaForm.img" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="影院名称" prop="cinemaName">
        <el-input v-model="cinemaForm.cinemaName" style="width: 500px"/>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="cinemaForm.phone" style="width: 500px"/>
      </el-form-item>
      <el-form-item label="地区" prop="area">
        <el-input v-model="cinemaForm.area" style="width: 500px"/>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="cinemaForm.address" type="textarea" style="border: outset"/>
      </el-form-item>
      <el-form-item style="padding-left: calc(50vh - 250px)">
        <el-button @click="this.$refs.cinemaForm.resetFields()" >重新输入</el-button>
        <el-button type="primary" @click="save" >保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {Plus} from "@element-plus/icons";
import request from "@/utils/request";
import {ElMessage} from "element-plus";
export default {
  name: "AddCinema",
  components:{
    Plus
  },
  data(){
    return{
      cinemaForm: {},
    }
  },
  methods:{
    save(){
      this.$refs.cinemaForm.validate().then(() => {
        request.post("/cinemas/saveCinema",this.cinemaForm).then(res => {
          console.log(res)
          if(res.code === '200'){
            ElMessage({
              showClose: true,
              type: "success",
              message: "添加影院信息成功！",
              center: true,
            })
          }
          else {
            ElMessage({
              showClose: true,
              type: "error",
              message: "添加影院信息失败！",
              center: true,
            })
          }
          this.$refs.cinemaForm.resetFields()
          // this.movieForm.img = null
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
    handleAvatarSuccess(res){
      this.cinemaForm.img = res;
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
  }
}
</script>

<style scoped>

/*上传头像*/
.avatar-uploader .avatar {
  width: 120px;
  height: 120px;
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
  width: 120px;
  height: 120px;
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