<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
        <div style="flex: 1;background-color: blue">
            <el-image style="width: 100%;height: 100%;" fit="cover"
                      src="https://bing.ee123.net/img/"/>
        </div>
        <div style="width: 400px">

            <router-view v-slot="{ Component }">
                <transition  name="el-fade-in" mode="out-in">
                    <component :is="Component" style="height: 100%"/>
                </transition>
            </router-view>

        </div>


    </div>

</template>


<script setup>

</script>

<style scoped>

</style>