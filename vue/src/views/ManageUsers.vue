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
      <el-table :data="userData"
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
        <el-table-column prop="phone" label="电话号码" width="120" />
        <el-table-column prop="address" label="地址" width="600" />

        <el-table-column fixed="right" label="Operations" width="120">
          <template #default="userList">
            <el-button type="text" style="height: 16px" @click="reUser(userList.row)">
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
                @confirm="delUser(userList.row.id)"
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
          @current-change="handleCurrentChange"
          class="page"
      />
    </div>
    <div>
      <el-dialog v-model="reUserDialog"  width="500px" center>
        <el-form :model="userForm" ref="reUserForm" label-width="auto" size="middle" class="managerForm" :rules="userRules">
          <el-form-item label="编号" prop="id">
            <p v-text="userForm.id" style="padding-left: 1vh"></p>
          </el-form-item>
          <el-form-item label="用户名" prop="username">
            <el-input  v-model="userForm.username"/>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="userForm.password" type="password" />
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
          <el-form-item label="地址" prop="address">
            <el-input v-model="userForm.address" type="textarea" style="border: outset"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="reUser(this.reback)">还原</el-button>
        <el-button type="primary" @click="saveReUser">保存</el-button>
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
  name: "ManagerUsers",
  components:{
    Search,
    Edit,
    Delete
  },
  data(){
    return{
      InfoFilled,
      value: 'id',
      pageSize: 10,
      currentPage: 1,
      total: 0,
      input: '',
      reUserDialog: false,
      form:{},
      reback:{},
      userData:[],
      userForm:{},


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
      ],
    }
  },
  created() {
    this.load();
  },
  methods:{
    // 加载表数据
    load(){
      console.log("success")
      console.log(this.managerData)
      // this.managerData.id = this.preFixInteger(this.managerData.id)
      request.get("/users/userFindPage",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          type: this.value,
          search: this.input,
        }

      }).then(res =>{
        console.log(res)
        this.userData = res.data.records
        this.total = res.data.total
        this.userData = this.add0UserId(this.userData)
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
    add0UserId(dic){
      for (let i = 0; i< dic.length;i++){
        dic[i].id = this.preFixInteger(dic[i].id)
      }
      return dic
    },
    // 修改数据
    reUser(row){
      this.reUserDialog = true
      this.userForm = JSON.parse(JSON.stringify(row))
      this.reback = JSON.parse(JSON.stringify(row))
    },
    saveReUser(){
      this.$refs.reUserForm.validate().then(()=>{
        this.userForm.birthday = dayjs(new Date(this.userForm.birthday)).format(' YYYY-MM-DD HH:mm:ss').toString()
        request.put("/users/updateUser",this.userForm).then(res => {
          console.log(res)
          this.reUserDialog = false
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
    delUser(id){
      console.log(id)
      request.delete("/users/" +  id).then(res => {
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

</style>