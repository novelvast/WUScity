//import './assets/main.css'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import VueAMap, {initAMapApiLoader} from '@vuemap/vue-amap';
import '@vuemap/vue-amap/dist/style.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import App from './App.vue'
import router from './router'

// 高德地图key
initAMapApiLoader({
    key: '3880909af22189a95e786615bf507073',
    securityJsCode: '553f957d7f0c860439265210158a4592', // 新版key需要配合安全密钥使用

})

const app = createApp(App)

// 注册图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(ElementPlus)
app.use(VueAMap)
app.use(router)

app.mount('#app')
