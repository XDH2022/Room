<template>
    <div style="text-align: center">
        <h1>重置密码</h1>
        <div style="margin: 30px">
            <div style="text-align: center;">
                <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">

                    <el-form-item prop="email">
                        <el-input type="text"  placeholder="邮箱" v-model="form.email">
                        </el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-row>
                            <el-form-item prop="code">
                                <el-col :span="20">
                                    <el-input type="text"  placeholder="验证码" v-model="form.code">
                                    </el-input>
                                </el-col>
                                <el-col :span="1">
                                </el-col>
                                <el-col :span="3" >
                                    <el-button type="success" plain @click="getValidateCode()" style="width: 100px" :disabled="!input ||cold>0">
                                        {{cold > 0? cold:"获取验证码"}}
                                    </el-button>
                                </el-col>
                            </el-form-item>

                        </el-row>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input type="password"  placeholder="密码" v-model="form.password">
                            <template #prefix>
                                <el-icon><Lock /></el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="password_repeat">
                        <el-input type="password"  placeholder="重复密码" v-model="form.password_repeat">
                            <template #prefix>
                                <el-icon><Lock /></el-icon>
                            </template>
                        </el-input>
                    </el-form-item>

                </el-form>
            </div>

            <div style="margin-top: 40px">
                <el-button type="success" style="width: 300px" plain @click="reset()">立即重置</el-button>
            </div>

        </div>
    </div>
</template>

<script setup>
import {Lock} from "@element-plus/icons-vue";
import router from "@/router";
import {reactive, ref} from "vue";
import {post} from "@/net";
import {ElMessage} from "element-plus";

const form = reactive({
    password:"",
    password_repeat:"",
    email:"",
    code:""
})


const validatePassword=(rule,value,callback)=>{
    if (value===''){
        callback(new Error("请再次输入密码"))
    }else if (value!==form.password){
        callback(new Error("密码不一致"))
    }else {
        callback()
    }
}


const validateEmail = (rule,value,callback) =>{
    if (value===''){
        callback(new Error("请输入邮箱"))
    }else if (!/^[A-Za-z0-9-_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)){
        callback(new Error("邮箱格式不正确"))
    }else {
        callback()
    }
}

const rules=reactive({
    password:[
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, message: '密码至少为6位', trigger: ['blur','change'] },
    ],
    password_repeat:[
        { required: true, message: '请输入密码', trigger: 'blur' },
        { validator: validatePassword,trigger: ['blur','change']},
    ],
    email:[
        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
        { validator: validateEmail,trigger: ['blur','change']},
    ],
    code:[
        { required: true, message: '请填写验证码', trigger: 'blur' },
        { min: 6,max:6, message: '验证码未6位', trigger: ['blur','change'] },

    ]
})


const input = ref(false)
const formRef = ref()

const onValidate= () =>{

    if (form.email!==""&&/^[A-Za-z0-9-_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(form.email))
        input.value = true

}

const cold = ref(0)//冷却按钮

const getValidateCode=()=>{
    cold.value = 60;
    post("/api/user/validateCode",{
        email:form.email,
        hasAccount:true,
    },(message)=>{
        ElMessage.success(message)
        console.log(message)
        setInterval(()=>cold.value--,1000)
    },(message)=>{
        ElMessage.warning(message)
        cold.value = 0;
    })
}

const reset=()=>{
    formRef.value.validate((isValid)=>{
        if (isValid){
            post("/api/user/resetPassword",{
                password:form.password,
                email:form.email,
                code:form.code
            },(message)=>{
                ElMessage.success(message)
                router.push("/")
            })
        }else {
            ElMessage.warning("有内容未填写")
        }
    })


}
</script>

<style scoped>

</style>