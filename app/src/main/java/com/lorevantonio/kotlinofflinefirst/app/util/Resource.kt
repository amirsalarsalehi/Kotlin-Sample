package com.lorevantonio.kotlinofflinefirst.app.util

sealed class Resource<T>(
    val data: T? = null,
    val msg: String? = null
) {

    class Success<T>(data: T) : Resource<T>(data)

    class Loading<T>(data: T?) : Resource<T>(data)

    class Error<T>(data: T?, msg: String) : Resource<T>(data, msg)

}