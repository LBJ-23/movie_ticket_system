<template>
  <div style="width: 100vh">
    <div style="display: flex;justify-content: center;padding: 20px;width: 100%">
      <el-select v-model="value" class="m-2"  style="width: 125px;padding-right: 20px">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
      <el-input v-model="input" placeholder="" style="width: 300px;margin-right: 20px" clearable/>
      <el-button type="primary" style="margin-top: 5px" @click="load">
        <el-icon style="vertical-align: middle;">
          <search />
        </el-icon>
        <span style="vertical-align: middle;"> Search </span>
      </el-button>
    </div>
    <div style="display: flex;justify-content: center">
      <el-table :data="managerData"
                style="width: 90%; border: groove"
                border
                stripe
                class="manager"
                :header-cell-style="{borderColor:'grey', fontSize: '14px', backgroundColor: '#87CEFA',textAlign:'center'}"
                :cell-style="{borderColor: 'grey',textAlign: 'center',color: 'black'}"

      >
        <el-table-column fixed prop="id" label="编号" min-width="80"/>
        <el-table-column fixed prop="username" label="用户名" min-width="100" />
        <el-table-column prop="password" label="密码" min-width="150" type="password"/>
        <el-table-column prop="sex" label="性别" min-width="60" />
        <el-table-column prop="birthday" label="出生日期" min-width="120" />
        <el-table-column prop="ascription" label="归属" min-width="300" />
        <el-table-column prop="phone" label="电话号码" width="120" />
        <el-table-column prop="address" label="地址" width="600" />

        <el-table-column fixed="right" label="Operations" width="120">
          <template #default="managerList">
            <el-button type="text" style="height: 16px" @click="reManager(managerList.row)">
              <el-icon style="vertical-align: middle;">
                <Edit />
              </el-icon>
            </el-button>
            <el-popconfirm
                confirm-button-text="确定"
                cancel-button-text="取消"
                :icon="InfoFilled"
                icon-color="red"
                title="确认删除该行数据吗？"
                @confirm="delManage(managerList.row.id)"
            >
              <template #reference>
                <el-button type="text" style="height: 16px">
                  <el-icon style="vertical-align: middle;">
                    <Delete />
                  </el-icon>
                </el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="demo-pagination-block" style="display: flex;justify-content: center;padding-top: 20px">
      <el-pagination
          v-model:currentPage="currentPage"
          layout="total, prev, pager, next, jumper"
          :total="total"
          v-model:page-sizes="pageSize"
          @current-change="handleCurrentChange"
          class="page"
      />
    </div>
    <div>
      <el-dialog v-model="reManagerDialog"  width="500px" center>
        <el-form :model="form" ref="reManagerForm" label-width="auto" size="middle" class="managerForm" :rules="rules">
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
              <el-option
                  v-for="item in asc"
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
import {Search,Edit,Delete,InfoFilled} from "@element-plus/icons";
import request from "@/utils/request";
import dayjs from "dayjs";
import {ElMessage} from "element-plus";



export default {
  name: "ManageManager",
  components:{
    Search,
    Edit,
    Delete,

  },
  // 加载页面时调用load()方法
  created(){
    this.load()
  },
  data(){
    return{
      // 下拉选择框
      value: 'id',
      pageSize: 10,
      currentPage: 1,
      total: 0,
      input: '',
      reManagerDialog: false,
      form:{},
      reback:{},
      InfoFilled,
      asc:{},

      options:[
        {
          value:'id',
          label:'编号',
        },
        {
          value: 'username',
          label: '用户名',
        },
        {
          value: 'phone',
          label: '电话号码',
        },
        {
          value: 'ascription',
          label: '归属',
        }
      ],

      managerData:[],

      // 校验规则
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
    // 加载表数据
    load(){

      console.log("success")
      console.log(this.managerData)
      // this.managerData.id = this.preFixInteger(this.managerData.id)
      request.get("/managers/managerFindPage",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          type: this.value,
          search: this.input,
        }

      }).then(res =>{
        console.log(res)
        this.managerData = res.data.records
        console.log('.....'+this.managerData)
        this.total = res.data.total
        this.managerData = this.add0ManagerId(this.managerData)
        request.get("/cinemas/getAllCinema").then(r => {
          r.data.unshift({id:'0',cinemaName:'系统管理员'})
          console.log(r.data)
          this.asc = r.data
          for (let i=0; i<this.managerData.length; i++){
            this.managerData[i].ascription = r.data[this.managerData[i].ascription].cinemaName
          }
        })
      })
    },
    // 改变当前页码数触发
    handleCurrentChange(){
      this.load()
    },
    // id填充0
    preFixInteger(num) {
      console.log((Array(6).join('0') + num).slice(-6))
      return (Array(6).join('0') + num).slice(-6)
    },
    add0ManagerId(dic){
      for (let i = 0; i< dic.length;i++){
        dic[i].id = this.preFixInteger(dic[i].id)
      }
      return dic
    },
    // 修改数据
    reManager(row){
      this.reManagerDialog = true
      this.form = JSON.parse(JSON.stringify(row))
      this.reback = JSON.parse(JSON.stringify(row))
    },
    saveReManager(){
      this.$refs.reManagerForm.validate().then(()=>{
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
          }
          else {
            ElMessage({
              showClose: true,
              type: "error",
              message: "修改数据失败！",
              center: true,
            })
          }
          this.load()
        })
      }).catch(() => {
            ElMessage({
              type: 'error',
              message: '所填数据有误，请重新确认！',
              showClose: true,
            })
      })
    },
    // 删除数据
    delManage(id){
      console.log(id)
      request.delete("/managers/" +  id).then(res => {
        if(res.code == '200'){
          ElMessage({
            showClose: true,
            type: "success",
            message: "删除数据成功！",
            center: true,
          })
        }
        else {
          ElMessage({
            showClose: true,
            type: "error",
            message: "删除数据失败！",
            center: true,
          })
        }
        this.load()
      })
    }
  }
}

</script>

<style scoped>
.manager{
}



</style>