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
</template>

<script>
import request from "@/utils/request";

export default {
  name: "cinemaDetails",
  data(){
    return{
      cinema:{},
      cinemaId: this.$route.query.cinemaId
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/cinemas/getCinema/" + this.cinemaId).then(res => {
        console.log(res.data)
        this.cinema = res.data
      })
    }
  },
}
</script>

<style scoped>

</style>