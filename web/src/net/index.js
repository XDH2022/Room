import axios from "axios"
import {ElMessage} from "element-plus";
import * as http from "http";
import router from "@/router";


const defaultError = (message) => {
    console.log(message)
}
const defaultFailure = (message) => {
    console.log(message)
    ElMessage.warning(message)
}

function post(url, data, success, failure = defaultFailure, error = defaultError) {

    axios.post(url, data, {
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        withCredentials: true
    }).then(({data}) => {
        console.log(data)
        if (data.code === 1) {
            success(data.data)//返回成功的内容
        } else if (data.code != null) {
            failure(data.msg)
        } else {
            error(data)
        }
    }).catch((errorIno) => {
        error(errorIno)
    })
}

function uploadPost(url, file, success, failure = defaultFailure, error = defaultError) {
    const formData = new FormData();
    formData.append('file', file);
    axios.post(url, formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        },
        withCredentials: true
    }).then(({data}) => {
        console.log(data)
        if (data.code === 1) {
            success(data.data)//返回成功的内容
        } else if (data.status != null) {
            failure(data.msg)
        } else {
            error(data)
        }
    }).catch((errorIno) => {
        error(errorIno)
    })
}

function get(url, success, failure = defaultFailure, error = defaultError) {
    axios.get(url, {
        withCredentials: true
    }).then(({data}) => {
        if (data.code===1) {
            success(data.data, data.code)//返回成功的内容
        } else {
            failure(data.msg, data.code)
        }

    }).catch((errorInfo) => {
        error(errorInfo)
    })
}

export {get, post, uploadPost}