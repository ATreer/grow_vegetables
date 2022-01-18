<template>
  <div>
    <el-button type="success" @click="$router.push('/main')">goMain</el-button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<style scoped>

</style>
