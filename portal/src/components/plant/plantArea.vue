<template>
  <el-card style="height: 100%">
    <el-row :gutter="20">
      <el-col :span="12"><el-popover
          placement="top-start"
          title="标题"
          width="200"
          trigger="hover">
        <el-descriptions :column="1" v-if="checkObj(params.gameObjId)">
          <el-descriptions-item label="id">{{params.id}}</el-descriptions-item>
          <el-descriptions-item label="名称">{{params.defaultName}}</el-descriptions-item>
          <el-descriptions-item label="描述">{{params.defaultDesc}}</el-descriptions-item>
        </el-descriptions>
        <el-descriptions :column="1" v-else>
          <el-descriptions-item label="id">{{params.id}}</el-descriptions-item>
          <el-descriptions-item label="名称">{{params.name}}</el-descriptions-item>
          <el-descriptions-item label="描述">{{params.desc}}</el-descriptions-item>
        </el-descriptions>
        <el-link @click="plantInfo" :type="checkObj(params.gameObjId)?'info':'success'" slot="reference">{{checkObj(params.gameObjId) ? params.defaultName : params.name}}</el-link>
      </el-popover></el-col>
      <el-col :span="12/operateList.length" v-for="operate in operateList">
        <el-link @click="operate.func" type="primary" >{{operate.name}}</el-link>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import {isNull} from "@/utils/SimpleUtils";
export default {
  name: "plantArea",
  props:{
    params:Object
  },
  data(){
    return {
      operateList:[]
    }
  },
  mounted() {
    this.operateList = [];
    if (this.checkObj(this.params.gameObjId)){
      this.operateList.push({ name: '播种',func:this.getPackageInfo})
      return;
    }
    if (isNull(this.params.status)){
      return;
    }
    let status = JSON.parse(this.params.status);

    if (status.indexOf(10001) !== -1){
      this.operateList.push({ name: '捉虫',func:this.getPackageInfo})
    }
    if (status.indexOf(10002) !== -1){
      this.operateList.push({ name: '灌溉',func:this.getPackageInfo})
    }
  },
  methods:{
    checkObj(param){
      return param === 0
    },getPackageInfo(){
      this.$emit('selectProps',this.params.id);
    },operate(){

    },plantInfo(){}
  }
}
</script>

<style scoped>

</style>