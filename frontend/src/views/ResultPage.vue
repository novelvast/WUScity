<script setup>
import { ref, onMounted } from 'vue'
import {checkCity, city} from "@/store/store.js"
import axios from "axios"
import {Cloudy, MostlyCloudy, Pouring, Sunny} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";

const input = ref("");

const baseURL = "http://47.115.205.56:5050"
//const baseURL = "http://localhost:3344"

const amapCenter = ref(["121.215281", "31.286028"]);

const ovoLoading = ref(true);
const geoLoading = ref(true);
const weatherLoading = ref(true);
const environmentLoading = ref(true);
const newsLoading = ref(true);
const mapLoading = ref(true);

const OVOData = ref({
  intro: "intro",
  gdp: "gdp",
  population: "population",
  collage: "collage",
  hospital: "hospital",
  cityQuantity: "cityQuantity",
  economy: "economy"
});

const geoData = ref({
  adcode: "adcode",
  location: "location",
  province: "province"
});

const weatherData = ref({
  current_condition: "current_condition",
  current_temperature: "current_temperature",
  dat_high_temperature: "dat_high_temperature",
  dat_low_temperature: "dat_low_temperature",
  dat_weather_icon_id: "0",
})

const environmentData = ref({
  aqi: "aqi",
  level: "level",
  category: "category",
  primary: "primary",
  pm10: "level",
  pm2p5: "pm2p5",
  no2: "level",
  so2: "so2",
  co: "co",
  o3: "level",
})

const newsData = ref([
  {
    title: "title1",
    ctime: "ctime1",
    url: "https://www.baidu.com",
    source: "source1",
  },

])

function fetchOVOData(city){
  ovoLoading.value = true;

  axios({
    method: "get",
    url: baseURL + "/ovo/" + city
  }).then(res => {
    Object.assign(OVOData.value, res.data.data)
    ovoLoading.value = false;
  })
}

function fetchGeoData(city){
  geoLoading.value = true;
  mapLoading.value = true;

  axios({
    method: "get",
    url: baseURL + "/geo/" + city
  }).then(res => {
    Object.assign(geoData.value, res.data.data)

    // 经纬度数据值切分到amapCenter中供amap使用
    amapCenter.value = geoData.value.location.split(",")
    geoLoading.value = false;
    mapLoading.value = false;
  })
}

function fetchWeatherData(city){
  weatherLoading.value = true;

  axios({
    method: "get",
    url: baseURL + "/weather/" + city
  }).then(res => {
    Object.assign(weatherData.value, res.data.data)
    weatherLoading.value = false;
  })
}

function fetchEnvironmentData(city){
  environmentLoading.value = true;

  axios({
    method: "get",
    url: baseURL + "/environment/" + city
  }).then(res => {
    Object.assign(environmentData.value, res.data.data)
    environmentLoading.value = false;
  })
}

function fetchNewsData(city){
  newsLoading.value = true;

  axios({
    method: "get",
    url: baseURL + "/news/" + city
  }).then(res => {
    Object.assign(newsData.value, res.data.data)
    newsLoading.value = false;
  })
}

function testAxios() {
  axios({
    method: "get",
    url: baseURL + "/test"
  }).then(res => {
    city.value = res.data;
    console.log(res.data)
    console.log(city)
  })
}

async function SearchBtnClick() {
  if(input.value !== "") {
    if(await checkCity(input.value)) {
      city.value = input.value;
      fetchGeoData(city.value)
      fetchOVOData(city.value);
      fetchWeatherData(city.value);
      fetchEnvironmentData(city.value);
      fetchNewsData(city.value);
    }
    else {
      ElMessage({
        showClose: true,
        message: '抱歉，该城市不存在',
        type: 'error',
      })
    }

  }

}

onMounted(() => {
  input.value = city.value;
  SearchBtnClick()
})


</script>

<template>
  <div class="result-page" >

    <h1 class="result-header">WUS city search</h1>
    <h2 class="result-header">{{ city }}</h2>
    <div class="result-search-wrapper">
      <el-input
        placeholder="城市.."
        v-model="input"
        size="large"
        type="text"
        @keyup.enter.native="SearchBtnClick"
        clearable
      />

      <el-button
          size="large"
          @click.native="SearchBtnClick"
          type="primary"
          icon="Search"
      >
        搜索
      </el-button>

      <div class="result-header" style="margin-top: 5px">*目前仅支持中国地级市和自治州</div>
    </div>

    <div class="result-wrapper">
      <el-row>
        <el-col :span="24">
          <el-card shadow="hover" class="bg-purple-dark" v-loading="ovoLoading">
            <template #header>
              <div class="card-header">
                <span>城市简介</span>
              </div>
            </template>
            <div>{{ OVOData.intro }}</div>
          </el-card>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="16" style="padding: 0">
          <el-row style="margin: 0">
            <el-col :span="12" >
              <el-row style="margin: 0">
                <el-col :span="24" style="padding: 0">
                  <el-card shadow="hover" class="bg-purple-dark" v-loading="environmentLoading">
                    <template #header>
                      <div class="card-header">
                        <span>环境</span>
                      </div>
                    </template>
                    <div>空气质量指数：{{ environmentData.aqi }}</div>
                    <div>空气质量：{{ environmentData.category }}</div>
                    <div>空气质量级别：{{ environmentData.level }}</div>
                    <div>主要污染物：{{ environmentData.primary }}</div>
                    <div>PM10：{{ environmentData.pm10 }}</div>
                    <div>PM2.5：{{ environmentData.pm2p5 }}</div>
                    <div>二氧化氮：{{ environmentData.no2 }}</div>
                    <div>二氧化硫：{{ environmentData.so2 }}</div>
                    <div>一氧化碳：{{ environmentData.co }}</div>
                    <div>臭氧：{{ environmentData.o3 }}</div>
                  </el-card>
                </el-col>

              </el-row>
              <el-row>
                <el-col :span="24" style="padding: 0">
                  <el-card shadow="hover" class="bg-purple-dark" v-loading="geoLoading">
                    <template #header>
                      <div class="card-header">
                        <span>地理信息</span>
                      </div>
                    </template>
                    <div>经纬度：{{ geoData.location }} </div>
                    <div>省份/直辖市：{{ geoData.province }}</div>
                    <div>地区编码：{{ geoData.adcode }} </div>
                  </el-card>
                </el-col>

              </el-row>
            </el-col>

            <el-col :span="12">
              <el-row style="margin: 0">
                <el-col :span="24" style="padding: 0">
                  <el-card shadow="hover" class="bg-purple-dark" v-loading="ovoLoading">
                    <template #header>
                      <div class="card-header">
                        <span>基本信息</span>
                      </div>
                    </template>
                    <div>GDP：{{ OVOData.gdp }} 亿元</div>
                    <div>人口数量：{{ OVOData.population }} 万人</div>
                    <div>高校数量：{{ OVOData.collage }} 所</div>
                    <div>医院数量：{{ OVOData.hospital }} 个</div>
                    <div>城市指数：{{ OVOData.cityQuantity }} </div>
                  </el-card>
                </el-col>
              </el-row>
              <el-row >
                <el-col :span="24" style="padding: 0">
                  <el-card shadow="hover" class="bg-purple-dark" v-loading="ovoLoading">
                    <template #header>
                      <div class="card-header">
                        <span>经济状况</span>
                      </div>
                    </template>
                    <div>{{ OVOData.economy }} </div>
                  </el-card>
                </el-col>
              </el-row>

            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <div v-loading="newsLoading" class="bg-purple-dark">
                <h3 style="margin: 20px">
                  <br/>新闻
                </h3>
                <el-table :data="newsData">
                  <el-table-column prop="title" label="标题" width="140">
                  </el-table-column>
                  <el-table-column prop="source" label="来源" width="80">
                  </el-table-column>
                  <el-table-column prop="url" label="链接">
                    <template #default="scope">
                      <a :href="scope.row.url" target=“_blank”> {{ scope.row.url }} </a>
                    </template>
                  </el-table-column>
                  <el-table-column prop="ctime" label="时间" width="140">
                  </el-table-column>
                </el-table>
              </div>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" class="bg-purple-dark" v-loading="weatherLoading">
            <template #header>
              <div class="card-header">
                <span>天气</span>
              </div>
            </template>
            <el-icon :size="30">
              <Sunny v-if="weatherData.dat_weather_icon_id==='0'"/>
              <Cloudy v-else-if="weatherData.dat_weather_icon_id==='1'"/>
              <MostlyCloudy v-else-if="weatherData.dat_weather_icon_id==='2'"/>
              <Pouring v-else-if="weatherData.dat_weather_icon_id==='7'"/>

            </el-icon>

            <div>当前天气：{{ weatherData.current_condition }}</div>
            <div>当前温度：{{ weatherData.current_temperature }} ℃</div>
            <div>当日最高温度：{{ weatherData.dat_high_temperature }} ℃</div>
            <div>当日最低温度：{{ weatherData.dat_low_temperature }} ℃</div>

          </el-card>

          <br/>
          <div class="amap-wrapper bg-purple-dark">
            <el-amap
                v-loading="mapLoading"
                class="amap-box"
                :vid="'amap-vue'"
                ref="map"
                :plugin="plugin"
                :center="amapCenter"
                :zoom="8"
            />
          </div>

        </el-col>
      </el-row>
    </div>


  </div>
</template>

<style scoped>

.result-page {
  text-align: center;
  padding-top: 60px;
}

.result-header {
  color: rgba(255, 255, 255, .65);
  margin: 30px;
}

.card-header {
  font-weight: bold;
}

.result-wrapper {
  margin-bottom: 50px;
}

.amap-wrapper {
  width: 100%;
  height: 400px;
  margin-top: 30px;
}


.el-input {
  width: 400px;
  margin-right: 10px;
}

.el-row {
  margin-top: 50px;
  position: relative;
  margin-bottom: 20px;
}


.el-col {
  position: relative;
  padding-left: 1cm;
  padding-right: 1cm;
  border-radius: 2px;
  display: inline-block;

}
.bg-purple-dark {
  background: #99a9bf;
}


.h3 {
  text-align: center;
  margin-top: 20px;
}

</style>





