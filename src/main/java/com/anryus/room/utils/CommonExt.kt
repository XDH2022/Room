package com.anryus.room.utils

import com.fasterxml.jackson.databind.ObjectMapper

val mapper = ObjectMapper()

fun<T> String.fromJson(clazz: Class<T>):T{
    return mapper.readValue(this,clazz)
}

fun<T> T.toJson():String{
    return mapper.writeValueAsString(this)
}