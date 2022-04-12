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
      <el-input v-model="input" placeholder="" style="width: 300px;margin-right: 20px" />
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
        <el-table-column prop="password" label="密码" min-width="150" />
        <el-table-column prop="sex" label="性别" min-width="60" />
        <el-table-column prop="birthday" label="出生日期" min-width="120" />
        <el-table-column prop="ascription" label="归属" min-width="120" />
        <el-table-column prop="phone" label="电话号码" width="120" />
        <el-table-column prop="address" label="地址" width="600" />

        <el-table-column fixed="right" label="Operations" width="120">
          <template #default="managerList">
            <el-button type="text" style="height: 16px" @click="reManager(managerList.row)">
              <el-icon style="vertical-align: middle;">
                <Edit />
              </el-icon>
            </el-button>

            <el-button type="text" style="height: 16px">
              <el-icon style="vertical-align: middle;">
                <Delete />
              </el-icon>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="demo-pagination-block" style="display: flex;justify-content: center;padding-top: 20px">
      <el-pagination
          v-model:currentPage="currentPage"
          layout="total, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          class="page"
      />
    </div>
    <div>
      <el-dialog v-model="reManagerDialog"  width="500px" center>
        <el-form :model="form" label-width="auto" size="middle" class="managerForm">
          <el-form-item label="编号" >
            <p v-text="form.id" style="padding-left: 1vh"></p>
          </el-form-item>
          <el-form-item label="用户名" >
            <el-input  v-model="form.username"/>
          </el-form-item>
          <el-form-item label="密码" >
            <el-input v-model="form.password" />
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="form.sex"  >
              <el-radio style="" label="男" />
              <el-radio label="女" />
              <el-radio label="保密" />
            </el-radio-group>
          </el-form-item>
          <el-form-item label="出生日期">
            <el-col :span="11">
              <el-date-picker
                  v-model="form.birthday"
                  type="date"
                  placeholder="请选择出生年月日"
                  style="width: 100%"
              />
            </el-col>
          </el-form-item>
          <el-form-item label="归属">
            <el-select v-model="form.ascription" placeholder="请选择归属地方">
              <el-option label="诚丰电影院" value="诚丰电影院" />
              <el-option label="银河电影院" value="银河电影院" />
            </el-select>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="form.phone" />
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address" type="textarea" style="border: outset"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="reManager(this.reback)">还原</el-button>
        <el-button type="primary" @click="reManagerDialog = false">保存</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>

</template>

<script>
import {Search,Edit,Delete} from "@element-plus/icons";
import request from "@/utils/request";
import dayjs from "_dayjs@1.11.0@dayjs";


export default {
  name: "ManageManager",
  components:{
    Search,
    Edit,
    Delete,
  },
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
      // 加载页面时调用load()方法

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

      managerData:[

      ]
    }
  },
  methods:{
    // 加载表数据
    load(){

      console.log("success")
      console.log(this.managerData)
      // this.managerData.id = this.preFixInteger(this.managerData.id)
      request.get("/managers/findPage",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          type: this.value,
          search: this.input,
        }

      }).then(res =>{
        console.log(res)
        this.managerData = res.data.records
        this.total = res.data.total
        this.managerData = this.add0ManagerId(this.managerData)

      })
    },
    handleSizeChange(){

    },
    handleCurrentChange(){

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
      this.form.birthday = dayjs(new Date(this.form.birthday)).format(' YYYY-MM-DD HH:mm:ss')
      this.reback = JSON.parse(JSON.stringify(row))
    },
  }
}

</script>

<style scoped>
.manager{
}



</style>