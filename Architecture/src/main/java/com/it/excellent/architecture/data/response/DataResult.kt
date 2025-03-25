package com.it.excellent.architecture.data.response

class DataResult<T>(
    private val mEntity: T,
    private val mResponseStatus: ResponseStatus
    ) {

    interface Result<T> {
        fun onResult(dataResult: DataResult<T>)
    }
}