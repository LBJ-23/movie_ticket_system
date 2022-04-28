<template>
  <div style="min-width: calc(100vh - 200px);min-height: 90vh;display:flex;align-items: center;justify-content: center;padding-top: 10px">
    <el-form :model="movieForm" ref="movieForm" label-width="120px" size="large" class="movieForm" :rules="movieRules" >
      <el-form-item label="海报" prop="img">
        <el-upload
            class="avatar-uploader"
            action="http://localhost:81/files/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
        >
          <img v-if="movieForm.img" :src="movieForm.img" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="电影名称" prop="movieName">
        <el-input v-model="movieForm.movieName" style="width: 500px"/>
      </el-form-item>
      <el-form-item label="放映时长" prop="duration">
        <el-input v-model="movieForm.duration"/>
      </el-form-item>
      <el-form-item label="电影类型" prop="type" style="width: 650px">
        <el-checkbox-group v-model="movieForm.type">
          <el-checkbox label="动作" />
          <el-checkbox label="喜剧" />
          <el-checkbox label="科幻" />
          <el-checkbox label="悬疑" />
          <el-checkbox label="恐怖" />
          <el-checkbox label="犯罪" />
          <el-checkbox label="冒险" />
          <el-checkbox label="战争" />
          <el-checkbox label="剧情" />
          <el-checkbox label="灾难" />
          <el-checkbox label="爱情" />
          <el-checkbox label="动画" />
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="上映日期" prop="releasedTime">
        <el-col :span="11">
          <el-date-picker
              v-model="movieForm.releasedTime"
              type="date"
              placeholder="请选择上映日期"
              style="width: 100%"
          />
        </el-col>
      </el-form-item>

      <el-form-item label="导演" prop="director">
        <el-input v-model="movieForm.director" />
      </el-form-item>
      <el-form-item label="主演" prop="actor">
        <el-input v-model="movieForm.actor" />
      </el-form-item>
      <el-form-item label="电影产地" prop="source">
        <el-input v-model="movieForm.source" />
      </el-form-item>
      <el-form-item label="电影简介" prop="introduce">
        <el-input v-model="movieForm.introduce" type="textarea" style="border: outset"/>
      </el-form-item>
      <el-form-item style="padding-left: calc(50vh - 250px)">
        <el-button @click="this.$refs.movieForm.resetFields()" >重新输入</el-button>
        <el-button type="primary" @click="save" >保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";
import { Plus } from '@element-plus/icons-vue'
import dayjs from "_dayjs@1.11.1@dayjs";
import request from "@/utils/request";
export default {
  name: "addMovie",
  components:{
    ElMessage,
    Plus,
  },
  data(){
    return{
      movieForm: {},
      movieRules: {
        img: [
          {required: true, message: '请选择一张电影海报',trigger: 'change'},
        ],
        movieName: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {max: 20, message: '电影名长度不能超过20个字符', trigger: 'change'},
        ],
        duration: [
          {required: true, message: '请输入电影时长',trigger: 'blur'},
        ],
        type: [{required: true, message: '请至少选择一种电影类型', trigger: 'change'}],
        releasedTime: [{required: true, message: '请选择上映日期', trigger: 'change'}],
        actor: [
          {required: true, message: '请输入主演信息',trigger: 'blur'},
        ],
        director: [
          {required: true, message: '请输入导演信息',trigger: 'blur'},
        ],
        source: [
          {required: true, message: '请输入电影产地',trigger: 'blur'},
        ],
        introduce: [
          {required: true, message: '请输入电影见简介', trigger: 'blur'},
          {max: 100 ,message: '地址长度不能超过100个字符',trigger: 'change'}
        ]
      },
    }
  },
  methods:{
    save(){
      this.$refs.movieForm.validate().then(() => {
        this.movieForm.releasedTime = dayjs(new Date(this.movieForm.releasedTime)).format(' YYYY-MM-DD HH:mm:ss').toString()
        console.log(this.movieForm.releasedTime)
        if (this.movieForm.type) {undefined
          this.movieForm.type = this.movieForm.type.join(",");
        }
        console.log(this.movieForm.typ)
        request.post("/movies/addMovie",this.movieForm).then(res => {
          console.log(res)
          if(res.code === '200'){
            ElMessage({
              showClose: true,
              type: "success",
              message: "添加电影信息成功！",
              center: true,
            })
          }
          else {
            ElMessage({
              showClose: true,
              type: "error",
              message: "添加电影信息失败！",
              center: true,
            })
          }
          this.$refs.movieForm.resetFields()
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
      this.movieForm.img = res;
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
  width: 80px;
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
  width: 80px;
  height: 120px;
  text-align: center;
}


.el-form-item__label {
  font-size: 20px;
}
.el-checkbox__input{
  border: 2px groove white;
}
</style>