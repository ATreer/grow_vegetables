<template>
  <el-card style="height: 100%">
    <el-row :gutter="20">
      <el-col :span="12"><el-popover
          placement="top-start"
          title="标题"
          width="200"
          trigger="hover">
        <el-descriptions :column="1" v-if="checkObj(params.plant)">
          <el-descriptions-item label="id">{{params.areaId}}</el-descriptions-item>
          <el-descriptions-item label="名称">{{params.areaName}}</el-descriptions-item>
          <el-descriptions-item label="描述">{{params.desc}}</el-descriptions-item>
        </el-descriptions>
        <el-descriptions :column="1" v-else>
          <el-descriptions-item label="id">{{params.plant.id}}</el-descriptions-item>
          <el-descriptions-item label="名称">{{params.plant.name}}</el-descriptions-item>
          <el-descriptions-item label="描述">{{params.plant.desc}}</el-descriptions-item>
        </el-descriptions>
        <el-link @click="plantInfo" :type="checkObj(params.plant)?'info':'success'" slot="reference">{{checkObj(params.plant) ? params.areaName : params.plant.name}}</el-link>
      </el-popover></el-col>
      <el-col :span="12/operateList.length" v-for="operate in operateList">
        <el-link @click="operate.func" type="primary" >{{operate.name}}</el-link>
      </el-col>
    </el-row>
    <packageDialog :dialogFormVisible="dialogFormVisible"></packageDialog>
  </el-card>
</template>

<script>
import {isNull} from "@/utils/SimpleUtils";
import packageDialog from "@/components/package/index"
export default {
  name: "plantArea",
  props:{
    params:Object
  },
  components:{packageDialog},
  data(){
    return {
      operateList:[],
      dialogFormVisible: false
    }
  },
  mounted() {
    this.operateList = [];
    if (this.checkObj(this.params.plant)){
      this.operateList.push({ name: '播种',func:this.getPackageInfo})
      return;
    }
    if (this.params.plant.status.indexOf(10001) !== -1){
      this.operateList.push({ name: '捉虫',func:this.getPackageInfo})
    }
    if (this.params.plant.status.indexOf(10002) !== -1){
      this.operateList.push({ name: '灌溉',func:this.getPackageInfo})
    }
  },
  methods:{
    checkObj(param){
      return isNull(param)
    },getPackageInfo(){
      this.dialogFormVisible = !this.dialogFormVisible;
    },operate(){

    },plantInfo(){}
  }
}
</script>

<style scoped>

</style>