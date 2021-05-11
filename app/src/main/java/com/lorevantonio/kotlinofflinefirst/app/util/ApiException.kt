package com.lorevantonio.kotlinofflinefirst.app.util

class ApiException(
    val msg: String
) {
    companion object {
        fun create(t: Throwable): ApiException = when (t) {
            is java.net.UnknownHostException -> {
                ApiException("Can not connect to Server !")
            }
            is java.net.ProtocolException -> {
                ApiException("Server Error")
            }
            is java.net.SocketTimeoutException -> {
                ApiException("Internet connection broken !")
            }
            else -> {
                ApiException("Unknown Exception Happened !")
            }
        }
    }
}