<template>
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
  <div style="width: 100%;justify-content: center;display: flex;flex-flow: column;align-items: center">
    <div style="width: 60%;min-width: 750px;border: 5px ridge;height: 250px;margin: 10px;display: flex;align-items: center" v-for="item in now">
      <router-link :to="{path:'/movieDetails',query:{id:item.id}}"></router-link>
      <div style="display: flex;align-items: center;justify-content: center;height: 150px;width: 25%">
        <img :src="item.img" style="height: 200px;">
      </div>
      <div style="width: 75%">
        <div style="height: 50px;font-size: 25px">
          <span>{{item.movieName}}</span>
        </div>
        <div style="height: 30px">
          <span>类型：{{item.type}}</span>
        </div>
        <div style="height: 30px">
          <span>主演：{{item.actor}}</span>
        </div>
        <div style="height: 30px">
          <span>上映时间：{{item.releasedTime}}</span>
        </div>
        <div style="height: 50px;padding-right: 50px;display: flex;justify-content: right;align-items: end">
          <router-link :to="{path:'/movieDetails',query: {id:item.id}}">
            <span>详情>></span>
          </router-link>
        </div>
      </div>
    </div>
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
</template>

<script>
import request from "@/utils/request";
import {Search} from "@element-plus/icons";

export default {
  name: "movie",
  components:{
    Search
  },
  data(){
    return{
      value: 'movie_name',
      pageSize: 5,
      currentPage: 1,
      total: 0,
      input: '',
      now:[],
      options: [
        {
          value: 'movie_name',
          label: '电影名称',
        },
        {
          value: 'type',
          label: '类别',
        },
        {
          value: 'actor',
          label: '主演',
        },
      ],
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/movies/earlyFindPage",{
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          type: this.value,
          search: this.input,
        }

      }).then(res =>{
        console.log(res)
        this.now = res.data.records
        this.total = res.data.total
      })
    },
    handleCurrentChange(){
      this.load();
    }
  },
}
</script>

<style scoped>

</style>