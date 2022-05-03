<template>
  <div style="display: flex;justify-content: center;padding: 20px;width: 100%;min-width: 1250px">
    <el-select v-model="value" class="m-2"  style="width: 150px;padding-right: 20px" placeholder="请选择地区" @change="load">
      <el-option
          v-for="item in options"
          :key="item"
          :label="item"
          :value="item"
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
  <div style="width: 100%;min-width:1250px;display: flex;flex-flow: column;align-items: center">
    <div style="width:70%;min-width:800px;display: flex;height: 100px;align-items: center;border-bottom: 1px solid gray" v-for="item in cinema">
      <div style="width: 80%;padding-left: 20px">
        <div style="padding-bottom: 5px">
          <span style="font-size: 25px">{{item.cinemaName}}</span>
        </div>
        <div>
          <span style="color: #8c939d">地址：{{item.address}}</span>
        </div>
      </div>
      <div style="width: 20%;display: flex;justify-content: right;padding-right: 20px">
        <el-button type="primary" style="border-radius: 20px" @click="to(item.id)">选座购票</el-button>
      </div>
    </div>
  </div>
  <div class="demo-pagination-block" style="display: flex;justify-content: center;padding-top: 20px;min-width: 1250px">
    <el-pagination
        v-model:currentPage="currentPage"
        layout="total,prev, pager, next, jumper"
        :total="total"
        v-model:page-size="pageSize"
        @current-change="handleCurrentChange"
        class="page"
    />
  </div>
</template>

<script>
import request from "@/utils/request";
import {Search} from "@element-plus/icons";
import router from "@/router";

export default {
  name: "cinema",
  created() {
    this.load();
  },
  data(){
    return{
      options:[],
      value:'全部',
      currentPage: 1,
      total: 0,
      pageSize: 8,
      cinema:{},
      input: "",
    }
  },
  components:{
    Search
  },
  methods:{
    load(){
      request.get("/cinemas/getArea").then(res => {
        res.data.unshift("全部")
        this.options = res.data
        // for(let i=0; i<res.data.length; i++){
        //   this.options.value[i] = res.data[i]
        // }
        console.log(res.data)
        console.log(this.options[1])
      })
      request.get("/cinemas/cinemaListFindPage",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          area: this.value,
          search: this.input,
        }

      }).then(res =>{
        console.log(res)
        this.cinema = res.data.records
        this.total = res.data.total
      })
    },
    handleCurrentChange(){
      this.load();
    },
    to(id){
      router.push({path:'/cinemaDetails',query:{cinemaId: id}})
    },
  }
}
</script>

<style scoped>

</style>