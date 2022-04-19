<template >

  <div style="height: 10vh;width: 100%">
    <Header/>
  </div>
  <div style="width: 100%;flex: 1">
    <router-view/>
  </div>
  <div style="height: 10vh;width: 100%">
    <Footer/>
  </div>


</template>

<script>
import Header from '@/components/Header.vue'
import Footer from "@/components/Footer";

export default {
  name: "Layout",
  components:{
    Footer,
    Header,
  }
}
</script>

<style scoped>

</style>