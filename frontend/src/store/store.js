import { ref } from 'vue'
import axios from "axios"

export let city = ref("");

// 校验城市名称是否合法
export async function checkCity(city) {
    let result;

    await axios({
        method: "get",
        url: "https://api.oioweb.cn/api/weather/weather?city_name=" + city
    }).then(res => {
        result = (res.data.result != null)

        console.log(result)
    })
    console.log(result)
    return result
}