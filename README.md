### 项目介绍

WUS city search是一个城市综合信息查询系统，基于SpringBoot + Vue实现，采用docker容器化部署，可查询中国境内的地级市和自治州的简介、经济状况、环境质量、地理位置、新闻等信息



### 效果演示

项目已在个人服务器部署，地址：http://47.115.205.56:5051/

#### 主页
![image](https://github.com/novelvast/WUScity/assets/113240184/0468c249-034b-402e-9012-659aa08bdd49)


#### 结果展示页
![image](https://github.com/novelvast/WUScity/assets/113240184/b8744bbf-c04e-41bc-a9b8-8dc03ae357c2)



### 技术选型

#### 后端技术

| 名称       | 说明            | 版本   |
| :--------- | :-------------- | :----- |
| SpirngBoot | Web应用开发框架 | 2.7.1  |
| jdk        | java开发工具    | 1.8    |
| webmagic   | java网络爬虫    | 0.7.5  |
| docker     | 应用容器引擎    | 24.0.6 |



#### 前端技术

| 名称       | 说明         | 版本  |
| ---------- | ------------ | ----- |
| Vue        | 前端框架     | 3.3.4 |
| Vue-router | 路由框架     | -     |
| Element    | 前端UI框架   | 2.4.0 |
| Axios      | 前端HTTP框架 | 1.5.1 |



#### 第三方api

| 名称            | 说明                 | 文档地址                                                   |
| --------------- | -------------------- | ---------------------------------------------------------- |
| 和风天气api     | 空气质量查询         | https://dev.qweather.com/docs/api/air/air-now/             |
| 高德地理编码    | 经纬度、地区编码查询 | https://lbs.amap.com/api/webservice/guide/api/georegeo     |
| 高德地图vue组件 | 高德地图vue组件      | https://lbs.amap.com/api/javascript-api-v2/getting-started |
| 天行数据api     | 地区新闻             | https://www.tianapi.com/apiview/154                        |
| 教书先生api     | 地区天气查询         | https://api.oioweb.cn/doc/weather/weather                  |



### 项目部署

本项目使用docker进行容器化部署，请确保有可用的docker环境

#### 后端部署

1、在终端执行`mvn package`将项目生成为jar包

2、将jar包与Dockerfile拷贝至同一目录下

3、拉取java基础镜像

```
docker pull openjdk:8
```

4、执行命令生成镜像

```bash
docker build -t 镜像名称 .
```

5、使用镜像生成示例

```bash
docker run -d -p 对外端口:3344 --name 实例名称 镜像名称
```



#### 前端部署

1、在终端执行`npm run build`将项目打包构建

2、将dist/文件夹、default.conf与Dockerfile拷贝至同一目录下

3、拉取nginx基础镜像

```
docker pull nginx
```

4、执行命令生成镜像

```bash
docker build -t 镜像名称 .
```

5、使用镜像生成示例

```bash
docker run -d -p 对外端口:80 --name 实例名称 镜像名称
```





### 欢迎联系

2272046066@qq.com

