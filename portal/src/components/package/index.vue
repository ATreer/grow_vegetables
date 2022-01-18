<template xmlns:el-col="http://www.w3.org/1999/html">
  <el-dialog title="背包" :visible.sync="dialogFormVisible">
    <el-row>
      <el-col :span="8" v-for="item in packages" style="margin-top: 10px">
        <el-tag type="success">{{item.name}}</el-tag>
        <el-link type="primary" >使用</el-link>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script>
import {post} from "@/utils/httpUtils";

export default {
  name: "packageDialog",
  props:{
    dialogFormVisible: Boolean
  },
  data(){
    return {
      packages:[]
    }
  },
  mounted() {
    post('/api/package/findAll',{growerId:1}).then(res =>{
      this.packages = res.data
    })
  }
}
</script>

<style scoped>

</style>