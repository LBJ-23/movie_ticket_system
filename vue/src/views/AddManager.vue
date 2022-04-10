<template>
  <div style="min-width: calc(100vh - 200px);min-height: 90vh;display:flex;align-items: center;justify-content: center">
    <el-form :model="form" label-width="120px" size="large" >
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
      <el-form-item label="用户名" >
        <el-input v-model="form.username" style="width: 500px"/>
      </el-form-item>
      <el-form-item label="密码" >
        <el-input v-model="form.password" />
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.sex">
          <el-radio label="男" />
          <el-radio label="女" />
          <el-radio label="保密" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-col :span="11">
          <el-date-picker
              v-model="form.birtheday"
              type="date"
              placeholder="请选择出生年月日"
              style="width: 100%"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="归属">
        <el-select v-model="form.ascription" placeholder="请选择归属地方">
          <el-option label="诚丰" value="shanghai" />
          <el-option label="银河" value="beijing" />
        </el-select>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="form.phone" />
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

      <el-form-item label="地址">
        <el-input v-model="form.address" type="textarea" />
      </el-form-item>
      <el-form-item style="padding-left: calc(50vh - 160px)">
        <el-button type="primary" @click="save" >保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
import {ElMessage} from "element-plus";
import { Plus } from '@element-plus/icons-vue'

export default {
  name: 'AddManager',
  components:{
    ElMessage,
    Plus,
  },
  data(){
    return{
      form: {}
    }
  },
  methods:{
    save(){

    }
  }
}

</script>

<style scoped>
.el-form-item{


}

/*上传头像*/
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
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