<template>
  <div style="height: 100%">
    <el-popover
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
      <el-link @click="plantInfo" slot="reference">{{checkObj(params.plant) ? params.areaName : params.plant.name}}</el-link>
    </el-popover>

  </div>
</template>

<script>
import {isNull} from "@/utils/SimpleUtils";

export default {
  name: "plantArea",
  props:{
    params:Object
  },
  data(){
    return {}
  },methods:{
    checkObj(param){
      return isNull(param)
    },plantInfo(){
      this.$axios.post('/api/package/findAll',{id:1},{emulateJSON: true}).then(res => {
        console.log(res.data)
      })
    }
  }
}
</script>

<style scoped>

</style>