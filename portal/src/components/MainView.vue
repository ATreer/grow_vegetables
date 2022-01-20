<template>
  <el-container>
    <el-header>Header</el-header>
    <el-main>
      <el-row :gutter="20" class="tabMenuClass">
        <el-col v-for="menu in menuList" :xs="6" :sm="6" :md="4" :lg="2" :xl="2" style="margin-top: 5px">
          <el-button>{{ menu.name }}</el-button>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="area_menu_class">
        <el-col :span="24">
          <el-link @click="refresh" type="primary" style="font-size: 17px;margin-top: 10px">刷新</el-link>
        </el-col>
      </el-row>
      <el-row :gutter="20" class="blessedLandArea">
        <el-col v-for="plant in plantAreaList" :xs="24" :sm="12" :md="8" :lg="6" :xl="6"
                style="height: 50px;text-align: left;margin-top: 10px">
          <plantArea @selectProps="selectProps" @refresh="refresh" :params="plant"></plantArea>
        </el-col>
      </el-row>
      <el-dialog title="背包" :visible.sync="dialogFormVisible">
        <packageDialog :param="currentAreaId" @refresh="refresh"></packageDialog>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script>
import plantArea from "@/components/plant/plantArea";
import packageDialog from "@/components/package/index"
import {post} from "@/utils/httpUtils";

export default {
  name: "MainView",
  inject: ["reload"],
  data() {
    return {
      menuList: [],
      packages: [],
      currentAreaId: '',
      dialogFormVisible: false,
      plantAreaList: []
    }
  },
  components: {
    plantArea, packageDialog
  },
  mounted() {
    let menus = [{name: '菜园'}, {name: '背包'}, {name: '冒险'}]
    this.menuList.push(...menus);
    post("/api/plantArea/findAll", {growerId: 1}).then(res => {
      if (res.data.code === 0) {
        this.plantAreaList = res.data.data
      }
    })
  },
  methods: {
    selectProps(id) {
      this.dialogFormVisible = !this.dialogFormVisible;
      this.currentAreaId = id + "";
    },
    refresh() {
      this.dialogFormVisible = false;
      post("/api/plantArea/findAll", {growerId: 1}).then(res => {
        if (res.data.code === 0) {
          this.plantAreaList = res.data.data
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.tabMenuClass {
  background-color: rgba(201, 201, 201, 0.95);
  height: 100%;
  padding: 20px;

  .grid-content {
    height: 50px;
  }
}

.blessedLandArea {
  margin-top: 10px;
  padding: 15px;
  background-color: rgba(255, 226, 203, 0.82);
}

.area_menu_class {
  background-color: rgba(255, 165, 104, 0.79);
  margin-top: 10px;
  text-align: left;
  font-size: 19px;
  height: 50px;
  border: #333333;
  border-radius: 5px;
}
</style>