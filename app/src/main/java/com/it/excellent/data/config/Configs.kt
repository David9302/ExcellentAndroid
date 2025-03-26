package com.it.excellent.data.config

import com.it.excellent.data.bean.User
import com.kunminx.architecture.data.config.keyvalue.KeyValueBoolean
import com.kunminx.architecture.data.config.keyvalue.KeyValueInteger
import com.kunminx.architecture.data.config.keyvalue.KeyValueSerializable
import com.kunminx.architecture.data.config.keyvalue.KeyValueString
import com.kunminx.keyvalue.annotation.KeyValueX


//TODO tip 1：消除 Android 项目 KeyValue 样板代码，让 key、value、get、put、init 缩减为一，不再 KV 爆炸。

@KeyValueX
interface Configs {

    fun token(): KeyValueString
    fun isLogin(): KeyValueBoolean
    fun alive(): KeyValueInteger
    fun user(): KeyValueSerializable<User>

}