<template>
    <el-container>
        <el-header>Header</el-header>
        <el-main>
          <el-row :gutter="20" class="tabMenuClass">
            <el-col v-for="menu in menuList" :xs="6" :sm="6" :md="4" :lg="2" :xl="2" style="margin-top: 5px"><el-button>{{menu.name}}</el-button></el-col>
          </el-row>
          <el-row :gutter="20" class="blessedLandArea" >
            <el-col v-for="plant in plantAreaList" :xs="24" :sm="12" :md="8" :lg="6" :xl="6" style="height: 50px;text-align: left;margin-top: 10px">
              <plantArea :params="plant"></plantArea>
            </el-col>
          </el-row>
        </el-main>
    </el-container>
</template>

<script>
import plantArea from "@/components/plant/plantArea";
    export default {
        name: "MainView",
      data(){
          return {
            menuList:[],
            plantAreaList:[],
            plants:[
              {areaId:1,areaName:'空地',plant:{id:1,name:'白菜',desc:'普通的白菜',status:[10001]},desc:'平平无奇的空地'},
              {areaId:2,areaName:'空地',plant:{id:2,name:'白菜',desc:'普通的白菜',status:[10001]},desc:'平平无奇的空地'},
              {areaId:3,areaName:'空地',plant:{id:3,name:'白菜',desc:'普通的白菜',status:[10001,10002]},desc:'平平无奇的空地'},
              {areaId:4,areaName:'空地',plant:{id:4,name:'白菜',desc:'普通的白菜',status:[]},desc:'平平无奇的空地'},
              {areaId:5,areaName:'空地',plant:{id:5,name:'白菜',desc:'普通的白菜',status:[10002]},desc:'平平无奇的空地'},
              {areaId:6,areaName:'空地',plant:{},desc:'平平无奇的空地'}
            ]
          }
      },
      components:{
        plantArea
      },
      mounted() {
          let num = Math.round(Math.random()*6)
        for (let i = 0; i < num; i++) {
          this.menuList.push({
            name : "菜单_".concat(i.toString())
          })
        }
        this.plantAreaList.push(...this.plants)
      }
    }
</script>

<style  lang="scss" scoped>
.tabMenuClass {
  background-color: rgba(201, 201, 201, 0.95);
  height: 100%;
  padding: 20px;
  .grid-content{
    height: 50px;
  }
}
.blessedLandArea{
  margin-top: 10px;
  padding: 15px;
  background-color: rgba(255, 226, 203, 0.82);

}
</style>