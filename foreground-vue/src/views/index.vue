<template>
  <div style="display: flex;justify-content: center;min-width: 1250px;height: 400px;align-items: center">
    <el-carousel :interval="5000" arrow="always" style="width: 80%;min-width: 1000px" >
      <el-carousel-item v-for="item in 4" :key="item">
        <img src="@/assets/img/1.png" style="width: 100%;height: 300px">
      </el-carousel-item>
    </el-carousel>
  </div>
  <div>
    <div style="display: flex;justify-content: center;min-width: 1250px;height: 400px;align-items: center">
      <div class="wrapper" style="width: 80%;min-width: 1000px">
        <swiper
            :slides-per-view="5"
            :space-between="20"
            @swiper="onSwiper"
            @slideChange="onSlideChange"
        >
          <swiper-slide>
            <img src="@/assets/img/movie1.png" style="height: 300px;width: 200px">
          </swiper-slide>
          <swiper-slide>
            <img src="@/assets/img/movie1.png" style="height: 300px;width: 200px">
          </swiper-slide>
          <swiper-slide>
            <img src="@/assets/img/movie1.png" style="height: 300px;width: 200px">
          </swiper-slide>
          <swiper-slide>
            <img src="@/assets/img/movie1.png" style="height: 300px;width: 200px">
          </swiper-slide>
          <swiper-slide>
            <img src="@/assets/img/movie1.png" style="height: 300px;width: 200px">
          </swiper-slide>
          <swiper-slide>
            <img src="@/assets/img/movie1.png" style="height: 300px;width: 200px">
          </swiper-slide>
          <swiper-slide>
            <img src="@/assets/img/movie1.png" style="height: 300px;width: 200px">
          </swiper-slide>
        </swiper>

      </div>
    </div>
  </div>
</template>

<script>

import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';

export default {
  name: "index",
  components:{
    Swiper,
    SwiperSlide,
  },
  methods: {
    onSwiper(swiper) {
      console.log(swiper);
    },
    onSlideChange() {
      console.log('slide change');
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