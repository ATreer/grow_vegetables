<template>
  <el-dialog title="背包" :visible.sync="dialogFormVisible">
    <el-row>
      <el-col :span="8" v-for="item in packages" style="margin-top: 10px">
        <el-tag type="success">{{item.name}}</el-tag>
        <el-link type="primary" @click="use(item)">使用</el-link>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import {post} from "@/utils/httpUtils";

export default {
  name: "packageDialog",
  props:{
    dialogFormVisible: Boolean,
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
        this.packages = res.data.data
      }else {
        this.$message.error("操作错误")
      }
    })
  },methods:{
    use(obj){
      post('/api/package/plantObj',{growerId:1,targetId:this.param,objId:obj.objId,useObjNum:1 }).then(res =>{
        if (res.data.code === 0){
          this.packages = res.data.data
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