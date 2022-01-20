<template>
  <el-row>
    <el-col :span="8" v-for="item in packages" style="margin-top: 10px">
        <el-popover
            placement="top-start"
            width="20"
            trigger="hover">
          <el-link type="primary" @click="use(item)">使用</el-link>
          <el-tag type="success" slot="reference">{{item.name}}</el-tag>
        </el-popover>
    </el-col>
  </el-row>
</template>

<script>
import {post} from "@/utils/httpUtils";

export default {
  name: "packageDialog",
  props:{
    param:String
  },
  data(){
    return {
      packages:[]
    }
  },
  mounted() {
    post('/api/package/findAll',{growerId:1}).then(res =>{
      if (res.data.code === 0){
        this.packages = res.data.data.filter(item => item.objType === 9)
      }else {
        this.$message.error("操作错误")
      }
    })
  },methods:{
    use(obj){
      post('/api/package/plantObj',{growerId:1,targetId:this.param,objId:obj.objId,useObjNum:1 }).then(res =>{
        if (res.data.code === 0){
          this.$emit("refresh")
        }else {
          this.$message.error("操作错误")
        }
      })

    }
  }
}
</script>

<style scoped>

</style>