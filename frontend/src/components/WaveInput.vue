<script setup>
import { ref, onMounted } from "vue";


defineProps({
  modelValue: String
})

onMounted(() => {
  const label = document.querySelector(".container label");
  const textList = label.innerText.split("");

  label.innerHTML = textList.map((letter, i) => `<span style="transition-delay: ${i * 30}ms;filter: hue-rotate(${i * 10}deg);">${letter}</span>`).join("");

})
</script>


<template>
  <div class="container">
    <input type="text" required :value="modelValue" @input="$emit('update:model-value',$event.target.value)">
    <label>WUS&nbsp;city &nbsp; search&nbsp; 城市搜索</label>
  </div>
</template>

<style>
/*google-fonts*/
@import url('https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.container {
  position: relative;
  width: 400px;

}

.container input {
  width: 100%;
  padding-block: 10px;
  background-color: transparent;
  border: none;
  outline: none;
  border-bottom: 3px solid #999;

  color: #d3d3d3;
  letter-spacing: 0.05em;
  font-size: 1.75em;
  transition: 0.5s;

  text-align:center
}
.container input:focus,
.container input:valid {
  border-bottom-color: #fff;
}
.container input:focus ~ label span,
.container input:valid ~ label span {
  color: #8aa4bb;
  text-shadow: 0 0 5px #8aa4bb, 0 0 15px #8aa4bb, 0 0 30px #8aa4bb;
  transform: translateY(-30px);
}

.container label {
  position: absolute;
  left: 0;

  font-size: 1.25em;
  color: rgba(255, 255, 255, .50);
  user-select: none;

  pointer-events: none;
}
.container label span {
  display: inline-flex;
  flex-direction: row;

  font-size: 1.50em;
  letter-spacing: 0.05em;
  transition: .25s cubic-bezier(0.5, 1, 0.5, 1.5);

}
</style>