<template>
  <el-card style="height: 100%">
    <el-row :gutter="20">
      <el-col :span="12"><el-popover
          placement="top-start"
          title="标题"
          width="200"
          @show="printTime"
          trigger="hover">
        <el-descriptions :column="1" v-if="checkObj(params.gameObjId)">
          <el-descriptions-item label="名称">{{params.defaultName}}</el-descriptions-item>
          <el-descriptions-item label="描述">{{params.defaultDesc}}</el-descriptions-item>
        </el-descriptions>
        <el-descriptions :column="1" v-else>
          <el-descriptions-item label="名称">{{params.name}}</el-descriptions-item>
          <el-descriptions-item label="描述">{{params.desc}}</el-descriptions-item>
          <el-descriptions-item label="产量">{{params.yield}}</el-descriptions-item>
          <el-descriptions-item label="成熟时间">{{matureTime}}</el-descriptions-item>
        </el-descriptions>
        <el-link @click="plantInfo" :type="checkObj(params.gameObjId)?'info':'success'" slot="reference">{{checkObj(params.gameObjId) ? params.defaultName : params.name}}</el-link>
      </el-popover></el-col>
      <el-col :span="12/operateList.length" v-for="operate in operateList">
        <el-link @click="operate.func(operate.param)" type="primary" >{{operate.name}}</el-link>
      </el-col>
      <el-col :span="12" v-if="isMature">
        <el-link @click="mature" type="primary" >收获</el-link>
      </el-col>
      <el-col :span="12" v-if="isPlant">
        <el-link @click="getPackageInfo" type="primary" >播种</el-link>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import {isNull} from "@/utils/SimpleUtils";
import {post} from "@/utils/httpUtils";
export default {
  name: "plantArea",
  props:{
    params:Object
  },
  watch:{
    params(){
      this.init();
    }
  },
  data(){
    return {
      operateList:[],
      matureTime:'',
      isMature: false,
      isPlant: false
    }
  },
  mounted() {
    this.init();
  },
  methods:{
    checkObj(param){
      return param === 0 || param === null
    },getPackageInfo(){
      this.$emit('selectProps',this.params.id);
    },
    init(){
      this.operateList = [];
      this.isMature = false
      this.isPlant = false
      if (this.checkObj(this.params.gameObjId)){
        this.isPlant = true
        return;
      }
      this.printTime();
      if (isNull(this.params.status)){
        return;
      }
      let status = JSON.parse(this.params.status);

      if (status.indexOf(10001) !== -1){
        this.operateList.push({ name: '捉虫',func:this.catchInsect,param: 10001})
      }
      if (status.indexOf(10002) !== -1){
        this.operateList.push({ name: '灌溉',func:this.catchInsect,param: 10002})
      }
    },
    catchInsect(status){
      post("/api/plantArea/catchInsect?dealStatus=" + status,this.params).then(res => {
        if (res.data.code === 0){
          this.$emit('refresh');
          this.$message.success("操作成功")
        }else {
          this.$message.success("操作失败")
        }
      })
    },mature(){
      post("/api/plantArea/mature",this.params).then(res => {
        this.$emit('refresh');
      })
    },
    plantInfo(){

    },printTime(){
      let newTime = new Date().getTime();
      let remainDate = newTime/1000 - this.params.plantTime;
      let remainTime = this.params.growCycle - remainDate;
      if (remainTime <= 0){
        this.matureTime = '成熟';
        this.isMature = true;
        this.operateList=[]
        return;
      }
      let second = Math.floor(remainTime % 60);
      let minute = Math.floor(remainTime / 60 % 60);
      let hours = Math.floor(remainTime / 60 / 60 % 60);
      let day = Math.floor(remainTime / 60 / 60 / 60 % 24);
      let time = "";
      if (day !== 0){
        time = time.concat(day+'',"天")
      }
      if (hours !== 0){
        time = time.concat(hours+'',"小时")
      }
      if (minute !== 0){
        time = time.concat(minute+'',"分钟")
      }
      if (second !== 0){
        time = time.concat(second+'',"秒")
      }
      this.matureTime = time
    }
  }
}
</script>

<style scoped>

</style>