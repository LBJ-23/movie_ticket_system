<template>
  <div style="width: 100%;min-width: 800px;min-height:350px;height: 40vh;background: skyblue;display: flex;">
    <div style="width: 40%;height:40vh;min-width:320px;min-height:350px;display: flex;justify-content: right;align-items: center">
      <div style="width: 210px;height: 310px;border: white 5px solid">
        <img :src=movie.img style="width: 200px;height: 300px">
      </div>
    </div>

    <div style="width: 60%;height: 40vh;min-width:320px;min-height:350px;padding-left: 30px;display: flex;flex-flow: column;justify-content: center">
      <div style="height: 80px;display: flex;align-items: center;font-size: 40px">
        <span>{{movie.movieName}}</span>
      </div>
      <div style="font-size: 20px">
        <span>{{movie.type}}</span>
      </div>
      <div style="font-size: 20px">
        <span>{{movie.source}}</span>
        <span> / </span>
        <span>{{movie.duration}}分钟</span>
      </div>
      <div style="font-size: 20px">
        <span>{{movie.releasedTime}} 中国大陆上映</span>
      </div>
      <div style="height: 160px;display: flex;align-items: end;padding-bottom: 30px">
        <el-button :color="bColor" style="width: 200px;height: 50px;font-size: 25px" :disabled="checkAfter">
            {{bmsg}}
        </el-button>
      </div>
    </div>
  </div>
  <div style="width: 100%;min-width: 800px;display: flex;justify-content: center;padding-top: 35px">
    <div style="width: 60%;">
      <div>
        <div style="border-left: 8px solid red;font-size: 30px">
          <span>电影简介</span>
        </div>
        <div style="padding-top: 15px">
          <span>{{movie.introduce}}</span>
        </div>
      </div>
      <div style="padding-top: 35px">
        <div style="border-left: 8px solid red;font-size: 30px">
          <span>演职人员</span>
        </div>
        <div style="padding-top: 15px">
          <span>导演：{{movie.director}}</span>
        </div>
        <div style="padding-top: 15px">
          <span>主演：{{movie.actor}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import request from "@/utils/request";

export default {
  name: "movieDetails",
  data(){
    return{
      bColor: "red",
      bmsg: "立即购票",
      id: this.$route.query.id,
      movie: {}
    }
  },
  created() {
    this.load()
  },
  computed:{
    checkAfter(){
      console.log(this.bColor)
      if(this.bColor === "grey"){
        return true
      }
    }
  },
  methods:{
    load(){
      request.get("movies/getMovie/" + this.id).then(res => {
        this.movie = res.data
        console.log(res)
        if(res.code === "201"){
          this.bmsg = "敬请期待"
          this.bColor = "grey"
        }
      })
    }
  }
}
</script>

<style scoped>

</style>