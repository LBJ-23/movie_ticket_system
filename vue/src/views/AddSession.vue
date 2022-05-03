<template>
  <div style="min-width: calc(100vh - 200px);min-height: 90vh;display:flex;align-items: center;justify-content: center">
    <el-form :model="sessionForm" ref="sessionForm" label-width="120px" size="large" class="sessionForm" >
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
              format="YYYY/MM/DD HH:mm:ss"
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
              format="YYYY/MM/DD HH:mm:ss"
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
      <el-form-item style="padding-left: calc(50vh - 250px)">
        <el-button @click="this.$refs.sessionForm.resetFields()" >重新输入</el-button>
        <el-button type="primary" @click="save" >保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import request from "@/utils/request";
import dayjs from "dayjs";
import {ElMessage} from "element-plus";

export default {
  name: "AddSession",
  data(){
    return{
      sessionForm: {},
      movie:[],
      cinemaId: sessionStorage.getItem("ascription") ? JSON.parse(sessionStorage.getItem("ascription")) : "",
      hall:[
        {
          hallName:'1号厅',
        },
        {
          hallName:'2号厅',
        },
      ]
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/movies/getAllMovie").then(res => {
        this.movie = res.data
      })
    },
    save(){
      this.$refs.sessionForm.validate().then(() => {
        //this.form.birthday = dayjs(new Date(this.form.birthday)).format(' YYYY-MM-DD HH:mm:ss').toString()
        //console.log(this.form.birthday)
        this.sessionForm.cinemaId = this.cinemaId
        request.post("/sessions/addSession",this.sessionForm).then(res => {
          console.log(res)
          if(res.code === '200'){
            ElMessage({
              showClose: true,
              type: "success",
              message: "添加场次信息成功！",
              center: true,
            })
          }
          else {
            ElMessage({
              showClose: true,
              type: "error",
              message: "添加场次信息失败！",
              center: true,
            })
          }
          this.$refs.sessionForm.resetFields()
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
    }
  }
}
</script>

<style scoped>

</style>