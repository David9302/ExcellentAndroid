package com.it.excellent.architecture.data.response

data class ResponseStatus(
    val responseCode:String? = "",
    val success: Boolean? = true,
    val source: Enum<ResultSource>? = ResultSource.NETWORK
)
