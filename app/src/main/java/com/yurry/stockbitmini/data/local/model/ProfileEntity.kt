package com.yurry.stockbitmini.data.local.model

data class ProfileEntity(
    val userName: String?= "",
    val password: String?= "",
    val loginFlag: String?= "",
    val token: String?= ""
)