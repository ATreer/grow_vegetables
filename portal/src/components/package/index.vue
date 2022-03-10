<template>
  <el-row>
    <el-col :span="4" v-for="item in packages" style="margin-top: 10px;margin-bottom: 10px;">
        <el-popover
            placement="top-start"
            width="20"
            trigger="hover">
          <el-link type="primary" @click="use(item)">使用</el-link>
          <div slot="reference" class="packageCellClass">
            <span>{{item.name}}</span>
          </div>
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

<style lang="scss" scoped>
.packageCellClass{
  background-color: rgba(199, 197, 192, 0.49);
  width: 50px;
  height: 50px;
  text-align: center;
  span {
    color: #1d9a07;
    margin: 18px 0;
    line-height: 16px;
    display: inline-block;
  }
}
</style>