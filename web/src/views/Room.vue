<script setup>

import router from "@/router";

let token = router.currentRoute.value.params.token
let webSocket = new WebSocket(`ws://localhost:8080/channel/${token}`)
webSocket.onopen = function (ev) {
}
webSocket.onmessage = function (ev) {
}
webSocket.onclose = function (ev) {
}
webSocket.onerror = function (ev) {
}

</script>

<template>

</template>

<style scoped>

</style>