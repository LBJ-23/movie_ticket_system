<template>
  <div style="display: flex;justify-content: center;min-width: 1250px;height: 600px;align-items: center">
    <el-carousel :interval="5000" arrow="always" style="width: 80%;min-width: 1000px" height="500px">
      <el-carousel-item v-for="item in 4" :key="item" style="display: flex;justify-content: center" >
        <img src="@/assets/img/1.png" style="height: 500px;width: 100%">
      </el-carousel-item>
    </el-carousel>
  </div>
  <div>
    <div style="display: flex;min-width: 1250px;align-items: center;justify-content: center">
      <div style="width: 40%">
        <span style="font-size: 50px"> 正在热映</span>
      </div>
      <div style="width: 40%;display: flex;justify-content: right;align-items: center"></div>
      <el-button type="text" style="font-size: 25px">更多>></el-button>
    </div>
    <div style="display: flex;justify-content: center;min-width: 1250px;height: 400px;align-items: center">
      <div class="wrapper" style="width: 80%;min-width: 1000px">
        <swiper
            :options="swiperOption"
            ref="mySwiper"
            :slides-per-view="3"
            class="swiper"
            @swiper="onSwiper"
            @slideChange="onSlideChange"
            
        >
          <swiper-slide v-for="item in now.slice(0,8)"  style="text-align: center" >
            <div>
              <img :src="item.img" style="height: 300px;width: 200px" >
            </div>
            <div>
              <span style=";width: 200px">{{item.movieName}}</span>
            </div>
          </swiper-slide>
        </swiper>
      </div>
    </div>
  </div>
  <div>
    <div style="display: flex;min-width: 1250px;align-items: center;justify-content: center">
      <div style="width: 40%">
        <span style="font-size: 50px">即将上映</span>
      </div>
      <div style="width: 40%;display: flex;justify-content: right;align-items: center"></div>
      <el-button type="text" style="font-size: 25px">更多>></el-button>
    </div>
    <div style="display: flex;justify-content: center;min-width: 1250px;height: 400px;align-items: center">
      <div class="wrapper" style="width: 80%;min-width: 1000px">
        <swiper
            :options="swiperOption"
            ref="mySwiper"
            :slides-per-view="3"
            class="swiper"
            @swiper="onSwiper"
            @slideChange="onSlideChange"

        >
          <swiper-slide v-for="item in soon.slice(0,8)"  style="text-align: center" >
            <div>
              <img :src="item.img" style="height: 300px;width: 200px" >
            </div>
            <div>
              <span style=";width: 200px">{{item.movieName}}</span>
            </div>
          </swiper-slide>
        </swiper>
      </div>
    </div>
  </div>

</template>

<script>


import { Swiper, SwiperSlide, Navigation} from 'swiper/vue';
import 'swiper/css';
import request from "@/utils/request";


export default {
  name: "index",
  components:{
    Swiper,
    SwiperSlide,
  },
  created() {
    this.load()
  },
  data(){
    return{
      now:[],
      soon:[],
      swiperOption:{
        loop:true,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        },
      },
    }
  },
  methods: {
    onSwiper(swiper) {
      console.log(swiper);
    },
    onSlideChange() {
      console.log('slide change');
    },
    load(){
      request.get("/movies/getEarlyMovie").then(res =>{
        console.log(res);
        this.now = res.data
      })
      request.get("/movies/getLateMovie").then(res =>{
        console.log(res);
        this.soon = res.data
      })
    }

  },

}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}



</style>