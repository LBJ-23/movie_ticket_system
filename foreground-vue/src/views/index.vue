<template>
  <div style="display: flex;justify-content: center;min-width: 1250px;height: 400px;align-items: center">
    <el-carousel :interval="5000" arrow="always" style="width: 80%;min-width: 1000px" >
      <el-carousel-item v-for="item in 4" :key="item">
        <img src="@/assets/img/1.png" style="width: 100%;height: 300px">
      </el-carousel-item>
    </el-carousel>
  </div>
  <div>
    <div style="display: flex;min-width: 1250px;align-items: center;justify-content: center">
      <div style="width: 40%">
        <span >正在热映</span>
      </div>
      <div style="width: 40%;display: flex;justify-content: right;align-items: center"></div>
      <el-button type="text">更多</el-button>
    </div>
    <div style="display: flex;justify-content: center;min-width: 1250px;height: 400px;align-items: center">
      <div class="wrapper" style="width: 80%;min-width: 1000px">
        <swiper
            :options="swiperOption"
            ref="mySwiper"
            :slides-per-view="4"
            class="swiper"
            @swiper="onSwiper"
            @slideChange="onSlideChange"
            
        >
          <swiper-slide v-for="item in movie.slice(0,8)"  style="text-align: center" >
            <div>
              <img :src="item.url" style="height: 300px;width: 200px" >
            </div>
            <div>
              <span style=";width: 200px">{{item.title}}</span>
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


export default {
  name: "index",
  components:{
    Swiper,
    SwiperSlide,
  },
  data(){
    return{
      movie:[
        {
          url: require('@/assets/img/movie1.png'),
          title: '超凡蜘蛛侠'
        },
        {
          url:require('@/assets/img/movie1.png')
        },
        {
          url:require('@/assets/img/movie1.png')
        },
        {
          url:require('@/assets/img/movie1.png')
        },
        {
          url:require('@/assets/img/movie1.png')
        },
        {
          url:require('@/assets/img/movie1.png')
        },{
          url:require('@/assets/img/movie1.png')
        },
        {
          url:require('@/assets/img/movie1.png')
        }
      ],
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
    //index.js
    prevImg(){
      console.log("prev")
    },
    nextImg(){
      console.log("next")

    },

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