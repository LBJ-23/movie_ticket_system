<template>
  <div style="min-width: calc(100vh - 200px);min-height: 90vh;display:flex;align-items: center;justify-content: center">
    <el-form :model="form" ref="managerForm" label-width="120px" size="large" class="managerForm" >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" style="width: 500px"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" type="password" show-password/>
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
        <el-select v-model="form.ascription" placeholder="请选择所属地方" style="width: 500px">
          <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.cinemaName"
              :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="form.address" type="textarea" style="border: outset"/>
      </el-form-item>
      <el-form-item style="padding-left: calc(50vh - 250px)">
        <el-button @click="this.$refs.managerForm.resetFields()" >重新输入</el-button>
        <el-button type="primary" @click="save" >保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>


<script>
import {ElMessage} from "element-plus";
import { Plus } from '@element-plus/icons-vue'
import request from "@/utils/request";
import dayjs from "dayjs";



export default {
  name: 'AddManager',
  components:{
    ElMessage,
    Plus,
  },
  created() {
    this.lood()
  },
  data(){
    return{
      form: {},
      options:{},
      // 添加表单校验规则
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
        ascription: [{required: true, message: '请选择所属地方', trigger: 'change'}],
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
      this.$refs.managerForm.validate().then(() => {
        this.form.birthday = dayjs(new Date(this.form.birthday)).format(' YYYY-MM-DD HH:mm:ss').toString()
        console.log(this.form.birthday)
        request.post("/managers/saveManager", this.form).then(res => {
          console.log(res)
          if(res.code === '200'){
            ElMessage({
              showClose: true,
              type: "success",
              message: "添加该名管理员信息成功！",
              center: true,
            })
          }
          else {
            ElMessage({
              showClose: true,
              type: "error",
              message: "添加该名管理员信息失败！",
              center: true,
            })
          }
          this.$refs.managerForm.resetFields()
        })
          }
      ).catch(() => {
        console.log("wrong")
        ElMessage({
          showClose: true,
          type: "error",
          message: "所填信息有误，请重新确认！",
          center: true
        })
      })
    },
    lood(){
      request.get("/cinemas/getAllCinema").then(res => {
        res.data.unshift({id:'0',cinemaName:'系统管理员'})
        console.log(res.data)
        this.options = res.data
      })
    },
  }
}

</script>

<style scoped>
.el-form-item{


}


</style>