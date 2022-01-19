<template>
    <el-container>
        <el-header>Header</el-header>
        <el-main>
          <el-row :gutter="20" class="tabMenuClass">
            <el-col v-for="menu in menuList" :xs="6" :sm="6" :md="4" :lg="2" :xl="2" style="margin-top: 5px"><el-button>{{menu.name}}</el-button></el-col>
          </el-row>
          <el-row :gutter="20" class="blessedLandArea" >
            <el-col v-for="plant in plantAreaList" :xs="24" :sm="12" :md="8" :lg="6" :xl="6" style="height: 50px;text-align: left;margin-top: 10px">
              <plantArea @selectProps="selectProps" :params="plant" ></plantArea>
            </el-col>
          </el-row>
          <packageDialog :dialogFormVisible="dialogFormVisible" :param="currentAreaId" @refresh="refresh"></packageDialog>
        </el-main>
    </el-container>
</template>

<script>
import plantArea from "@/components/plant/plantArea";
import packageDialog from "@/components/package/index"
import {post} from "@/utils/httpUtils";
    export default {
        name: "MainView",
      data(){
          return {
            menuList:[],
            packages:[],
            currentAreaId:'',
            dialogFormVisible:false,
            plantAreaList:[]
          }
      },
      components:{
        plantArea,packageDialog
      },
      mounted() {
          let num = Math.round(Math.random()*6)
        for (let i = 0; i < num; i++) {
          this.menuList.push({
            name : "菜单_".concat(i.toString())
          })
        }
        post("/api/plantArea/findAll",{growerId:1}).then(res => {
          if (res.data.code === 0){
            this.plantAreaList = res.data.data
          }
        })
      },
      methods:{
          selectProps(id){
            this.dialogFormVisible = !this.dialogFormVisible;
            this.currentAreaId = id+"";
          },
        refresh(){
          post("/api/plantArea/findAll",{growerId:1}).then(res => {
            if (res.data.code === 0){
              this.plantAreaList = res.data.data
            }
          })
          this.dialogFormVisible =! this.dialogFormVisible;
        }
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