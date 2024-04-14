<template>
  <div style="background-color: white;text-align: center">
    <h1>登录</h1>
    <div style="margin:30px">
      <div style="margin-top: 30px">
        <el-input type="text" placeholder="Username" style="margin-top: 10px" v-model="form.username">
          <template #prefix>
            <el-icon>
              <User/>
            </el-icon>
          </template>
        </el-input>
        <el-input type="password" placeholder="Password" style="margin-top: 10px" v-model="form.password">
          <template #prefix>
            <el-icon>
              <Lock/>
            </el-icon>
          </template>
        </el-input>
        <el-row style="margin-top: 10px">
          <el-col :span="12" style="text-align: left">
            <el-checkbox v-model="form.remember" label="记住密码"/>
          </el-col>
          <el-col :span="12" style="text-align: right">
            <el-link @click="router.push('/forget')">忘记密码</el-link>
          </el-col>
        </el-row>
        <div style="margin-top: 40px">
          <el-button type="success" style="width: 300px" plain @click="login()">登录</el-button>
        </div>
        <el-divider>
          或者
        </el-divider>
        <div style="margin-top: 40px">
          <el-button type="warning" style="width: 300px" @click="router.push('/register')" plain>注册</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {Lock, User} from "@element-plus/icons-vue";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {get, post} from "@/net";
import router from "@/router";
import axios from "axios";

const form = reactive({
  username: "",
  password: "",
  remember: false
})

const login = () => {
  console.log(axios.defaults.baseURL)

  if (!form.username || !form.password) {
    ElMessage.warning("请填写用户名和密码")
  } else {
    post("/api/user/login", {
      username: form.username,
      password: form.password,
      remember: form.remember
    }, (result) => {
      router.push(`/room/${result}`)
    }, (message) => {
      ElMessage.error(message)
    })
  }
}

</script>

<style scoped>
</style>