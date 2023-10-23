<script setup>
import { ref } from 'vue'
import MagicButton from "@/components/MagicButton.vue";
import WaveInput from "@/components/WaveInput.vue";
import { city, checkCity } from "@/store/store.js"
import router from "@/router";
import {ElMessage} from "element-plus";

const input = ref("");



async function SearchBtnClick() {
  if(input.value !== "") {
    if(await checkCity(input.value)) {
      console.log("1")
      city.value = input.value;
      await router.push({ name: "ResultPage" });
    }
    else {
      console.log("2")
      ElMessage({
        showClose: true,
        message: '抱歉，该城市不存在',
        type: 'error',
      })
    }



  }
}

</script>

<template>

  <div class="home-page">
    <WaveInput v-model="input" style="margin: 50px"/>
    <MagicButton @click="SearchBtnClick"/>
  </div>


</template>


<style>

.home-page {
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;

}

</style>
