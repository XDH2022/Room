<script setup>

import router from "@/router";
import {Chat} from "@/entity/Chat";
import {reactive, ref} from "vue";
import {get} from "@/net";

let token = router.currentRoute.value.params.token
let webSocket = new WebSocket(`ws://localhost:8080/channel/${token}`)
webSocket.onopen = function (ev) {
}
webSocket.onmessage = function (ev) {
  message.queue.push(ev)
}
webSocket.onclose = function (ev) {
}
webSocket.onerror = function (ev) {
}

let inputValue = ref("")
let message = reactive({
  queue:[]
})
let roomInfo = reactive({
  roomName:"",
  roomId:1,
})

let rooms = ref([])

function sendMessage(content) {
  let msg =  new Chat(2,1,1,2,content).toJson()
  webSocket.send(msg)
  message.queue.push(msg)
  inputValue.value = ""
}

function getRoomChatHistory(){
  get(`/api/room/history?roomId=${roomInfo.roomId}`,(data) =>{
    message.queue.push(data)
  })
}

function getRooms(){
  get("/api/room/list" , (data) => {
    rooms.value = data
  })
}

function init(){
  getRoomChatHistory()
  getRooms()
}

init()

</script>

<template>
  <div style="width: 100vw;height: 100vh">
    <el-container>
      <el-aside width="200px">
        <div v-for="item in rooms">

        </div>
      </el-aside>
      <el-container>
        <el-main>
          <div style="height: 90vh">
            <div v-for="item in message.queue">

            </div>
          </div>
        </el-main>
        <el-footer>
          <el-row>
            <el-col :span="8" :push="8" class="inputBar">
              <el-input type="textarea" v-model="inputValue"/>
            </el-col>
            <el-col :span="4" :push="8">
              <el-button class="el-button--success" v-on:click="sendMessage(inputValue)" v-bind:disabled="!inputValue.length>0">Send</el-button>
            </el-col>
          </el-row>
        </el-footer>
      </el-container>
    </el-container>

  </div>

</template>

<style>

</style>
