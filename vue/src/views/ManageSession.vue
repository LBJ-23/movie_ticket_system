<template>
  <div style="width: 100vh">
  <div style="display: flex;justify-content: center;padding: 20px;width: 100%">
    <el-select v-model="dateValue" class="m-2"  style="width: 200px;padding-right: 20px" @change="load">
      <el-option
          v-for="item in date"
          :key="item.id"
          :label="item.value"
          :value="item.id"
      />
    </el-select>
    <el-select v-model="movieValue" class="m-2"  style="width: 250px;padding-right: 20px" @change="load">
      <el-option
          v-for="item in movie"
          :key="item.id"
          :label="item.movieName"
          :value="item.id"
      />
    </el-select>
  </div>
  <div style="display: flex;justify-content: center">
    <el-table :data="sessionData"
              style="width: 90%; border: groove"
              border
              stripe
              class="session"
              :header-cell-style="{borderColor:'grey', fontSize: '14px', backgroundColor: '#87CEFA',textAlign:'center'}"
              :cell-style="{borderColor: 'grey',textAlign: 'center',color: 'black'}"

    >
      <el-table-column fixed prop="sessionId" label="编号" min-width="80"/>
      <el-table-column fixed prop="movieName" label="电影名称" min-width="250" />
      <el-table-column prop="hall" label="放映厅" min-width="100"/>
      <el-table-column prop="start" label="开始时间" min-width="200"/>
      <el-table-column prop="end" label="结束时间" min-width="200" />
      <el-table-column prop="price" label="价格" min-width="100"/>

      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="sessionList">
          <el-button type="text" style="height: 16px" @click="reSession(sessionList.row)">
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
              @confirm="delSession(sessionList.row.id)"
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
        layout="total,prev, pager, next, jumper"
        :total="total"
        v-model:page-size="pageSize"
        @current-change="handleCurrentChange"
        class="page"
    />
  </div>
  <div>
    <el-dialog v-model="reSessionDialog"  width="500px" center>
      <el-form :model="sessionForm" ref="sessionForm" label-width="120px" size="large" class="sessionForm" >
        <el-form-item label="编号" prop="id">
          <p v-text="sessionForm.sessionId" style="padding-left: 1vh"></p>
        </el-form-item>
        <el-form-item label="电影名称" prop="movieId">
          <el-select v-model="sessionForm.movieId" placeholder="请选择电影名称">
            <el-option
                v-for="item in movie"
                :key="item.id"
                :label="item.movieName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="放映厅" prop="hall">
          <el-select v-model="sessionForm.hall" placeholder="请选择放映厅">
            <el-option
                v-for="item in hall"
                :key="item.hallName"
                :label="item.hallName"
                :value="item.hallName"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="start">
          <div class="start">
            <el-date-picker
                v-model="sessionForm.start"
                type="datetime"
                placeholder="请选择开始时间"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DD H:m:s"
            />
          </div>
        </el-form-item>
        <el-form-item label="结束时间" prop="end">
          <div class="end">
            <el-date-picker
                v-model="sessionForm.end"
                type="datetime"
                placeholder="请选择结束时间"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DD H:m:s"
            />
          </div>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-input v-model="sessionForm.type" style="width: 500px"/>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="sessionForm.price" style="width: 500px"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="reSession(this.reback)">还原</el-button>
        <el-button type="primary" @click="saveReSession">保存</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
  </div>
</template>

<script>
import {InfoFilled,Edit,Delete} from "@element-plus/icons";
import request from "@/utils/request";
import {ElMessage} from "element-plus";
export default {
  name: "ManageSession",
  created() {
    this.load()
    this.date.push({id:5,value: this.getTime(1)+" 之前"})
    this.date.push({id:1,value: this.getTime(1)+" 今天"})
    this.date.push({id:2,value: this.getTime(2)+" 明天"})
    this.date.push({id:3,value: this.getTime(3)+" 后天"})
    this.date.push({id:4,value: this.getTime(3)+" 之后"})
  },
  components:{
    Edit,
    Delete,
  },
  data(){
    return{
      InfoFilled,
      sessionForm: {},
      sessionData: [],
      pageSize: 10,
      currentPage: 1,
      total: 0,
      movie:[],
      movieValue: 0,
      dateValue: 0,
      cinemaId: sessionStorage.getItem("ascription") ? JSON.parse(sessionStorage.getItem("ascription")) : "",
      reSessionDialog: false,
      hall:[
        {
          hallName:'1号厅',
        },
        {
          hallName:'2号厅',
        },
      ],
      date:[
        {
          id: 0,
          value: "全部时间",
        },
      ]
    }
  },
  methods:{
    load(){
      request.get("/sessions/sessionFindPage",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          date: this.dateValue,
          movie: this.movieValue,
          cinema: this.cinemaId,
        }

      }).then(res =>{
        console.log(res)
        this. sessionData = res.data.records
        for (let i=0;i<this.sessionData.length;i++){
          this.sessionData[i].sessionId = i+1;
        }
        console.log(this.sessionData)
        this.total = res.data.total
        request.get("/movies/getAllMovie").then(res => {
          res.data.unshift({id:0,movieName:'全部电影'})
          this.movie = res.data
          for (let i=0;i<this.sessionData.length;i++){
            this.sessionData[i]=Object.assign(this.sessionData[i],{movieName:res.data[this.sessionData[i].movieId].movieName})
            console.log(this.sessionData[i])

          }
        })
      })
    },
    //获取时间
    getTime(id){
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth()+1;
      let dd = new Date().getDate();
      if (id === 2){
        dd = dd + 1
      }
      if (id === 3){
        dd = dd + 2
      }
      console.log(yy+'-'+mm+'-'+dd)
      return yy+'-'+mm+'-'+dd
    },
    // 改变当前页码数触发
    handleCurrentChange(){
      this.load()
    },
    // 修改数据
    reSession(row){
      this.reSessionDialog = true
      this.sessionForm = JSON.parse(JSON.stringify(row))
      this.reback = JSON.parse(JSON.stringify(row))
    },
    saveReSession(){
      this.$refs.sessionForm.validate().then(()=>{
        request.put("/sessions/updateSession",this.sessionForm).then(res => {
          console.log(res)
          this.reSessionDialog = false
          if(res.code === '200'){
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
    delSession(id){
      console.log(id)
      request.delete("/sessions/delSession/" +  id).then(res => {
        if(res.code === '200'){
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
    },
  }
}
</script>

<style scoped>

</style>