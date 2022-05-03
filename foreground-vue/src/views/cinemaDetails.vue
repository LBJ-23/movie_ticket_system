<template>
  <div style="width: 100%;min-width: 800px;min-height:200px;height: 35vh;background: skyblue;display: flex;">
    <div style="width: 25%;height:35vh;min-width:200px;min-height:200px;display: flex;justify-content: right;align-items: center">
      <div style="width: 160px;height: 160px;border: white 5px solid">
        <img :src=cinema.img style="width:150px;height: 150px">
      </div>
    </div>

    <div style="width: 75%;height: 35vh;min-width:600px;min-height:200px;padding-left: 30px;display: flex;flex-flow: column;justify-content: center">
      <div style="height: 80px;display: flex;align-items: center;font-size: 40px">
        <span>{{cinema.cinemaName}}</span>
      </div>
      <div style="font-size: 20px">
        <span>{{cinema.address}}</span>
      </div>
      <div style="font-size: 20px;padding-top: 5px">
        <span>{{cinema.phone}}</span>
      </div>
    </div>
  </div>
  <div style="width: 100%;min-width: 800px;display: flex;flex-flow: column;align-items: center;padding-top: 20px">
    <div style="width: 75%;border-left: red 8px solid;padding-left: 5px">
      <span>电影排期</span>
    </div>
    <div style="height: 160px">
      <img :src="movieInfo.img" style="width: 100px;height: 150px">
    </div>
    <div style="height: 30px">
      <span>{{movieInfo.duration}}分钟 | {{movieInfo.type}}</span>
    </div>
    <div style="height: 50px">
      <el-select v-model="movieValue" class="m-2"  style="width: 250px" @change="load">
        <el-option
            v-for="item in movie"
            :key="item.id"
            :label="item.movieName"
            :value="item.id"
        />
      </el-select>
    </div>
    <div style="height: 50px">
      <el-select v-model="dateValue" class="m-2"  style="width: 250px" @change="load">
        <el-option
            v-for="item in date"
            :key="item.id"
            :label="item.value"
            :value="item.id"
        />
      </el-select>
    </div>
    <div style="width: 60%">
      <div style="display: flex;height: 100px;align-items: center;border-bottom: 1px solid gray" v-for="item in sessionList">
        <div style="width: 20%;padding-left: 20px">
          <div style="padding-bottom: 5px">
            <span style="font-size: 25px">{{this.formatTime(item.start)}}</span>
          </div>
          <div>
            <span style="color: #8c939d">{{this.formatTime(item.end)}}散场</span>
          </div>
        </div>
        <div style="width: 60%;padding-left: 20px">
          <div style="padding-bottom: 5px">
            <span style="font-size: 20px">{{item.type}}</span>
          </div>
          <div>
            <span style="font-size: 20px">{{item.hall}}</span>
          </div>
        </div>
        <div style="width: 10%;padding-left: 20px">
          <span style="font-size: 20px">￥{{item.price}}</span>
        </div>
        <div style="width: 20%;display: flex;justify-content: right;padding-right: 20px">
          <el-button   style="border-radius: 20px;border: red 1px solid;color: red">购票</el-button>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "cinemaDetails",
  data(){
    return{
      cinema:{},
      movie:[],
      date:[],
      dateValue:1,
      movieValue: 1,
      movieInfo:{},
      sessionList:[],
      cinemaId: this.$route.query.cinemaId
    }
  },
  created() {
    this.load()
    this.date.push({id:1,value: this.getTime(1)+" 今天"})
    this.date.push({id:2,value: this.getTime(2)+" 明天"})
    this.date.push({id:3,value: this.getTime(3)+" 后天"})
  },
  methods:{
    load(){
      request.get("/cinemas/getCinema/" + this.cinemaId).then(res => {
        console.log(res.data)
        this.cinema = res.data
      })
      request.get("/movies/getEarlyMovie").then(res => {
        this.movie = res.data
      })
      request.get("/movies/getMovie/" + this.movieValue).then(res => {
        this.movieInfo = res.data
      })
      request.get("/sessions/getSessionList",{
        params:{
          movieId: this.movieValue,
          cinemaId: this.cinemaId,
          date: this.dateValue
        }
      }).then(res => {
        console.log(res)
        this.sessionList = res.data
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
    formatTime(date){
      let d = new Date(date)
      console.log("1111"+ d)
      let hh = d.getHours().toString().padStart(2,'0')
      let mm = d.getMinutes().toString().padStart(2,'0');
      return hh + ':' + mm
    }
  },
}
</script>

<style scoped>

</style>