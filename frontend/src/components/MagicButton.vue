<script setup>
import { onMounted } from 'vue'

onMounted(() => {

  const btns = document.querySelectorAll(".btn");

  btns.forEach(btnEle => {
    btnEle.addEventListener("mousemove", e => {
      const x = e.offsetX,
          y = e.offsetY,
          btnWidth = btnEle.clientWidth,
          btnHeight = btnEle.clientHeight,
          transX = x - btnWidth / 2,
          transY = y - btnHeight / 2;

      btnEle.style.transform = `translateX(${transX}px) translateY(${transY}px)`;

      const mx = e.pageX - btnEle.offsetLeft,
          my = e.pageY - btnEle.offsetTop;

      btnEle.style.setProperty("--x", mx + "px");
      btnEle.style.setProperty("--y", my + "px");
    });

    btnEle.addEventListener("mouseout", () => {
      btnEle.style.transform = "";
    });
  });

})
</script>

<template>
  <a href="#" class="btn" style="--clr: #29aeef">
    <span>
      搜索
    </span>
  </a>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* 按钮样式，包括背景、文本和过渡属性 */
.btn {
  position: relative;

  width: 200px;
  display: flex;
  justify-content: center;
  align-items: center;

  padding: 15px 20px;
  background-color: #333;
  border-radius: 40px;

  text-decoration: none;
  text-transform: uppercase;
  font-size: 1.5em;
  font-weight: 600;
  letter-spacing: .1em;

  transition: transform .1s;
  overflow: hidden;
}
.btn span {
  position: relative;
  color: rgba(255, 255, 255, .65);
  transition: color .5s;
  pointer-events: none;
}
/* 悬停时文本颜色变化 */
.btn:hover span {
  color: rgba(255, 255, 255, 1);
}


/* 悬停时会扩展开的圆圈样式 */
.btn::before {
  content: "";
  position: absolute;
  top: var(--y);
  left: var(--x);
  transform: translate(-50%, -50%);
  background-color: var(--clr);
  width: 0;
  height: 0;
  border-radius: 50%;
  transition: 1s, left 0s, top 0s;
}
.btn:hover::before {
  width: 200px;
  height: 200px;
}
</style>