package com.lorevantonio.kotlinofflinefirst.app.util

import android.util.Log
import retrofit2.Response

open class ApiResponse<T> {

    enum class ErrorStatus { Unauthorized, Forbidden, NotFound, Unknown }

    fun <T> create(t: Throwable): ApiResponse<T> = if (t.message == null)
        ApiErrorResponse("Unknown Execption !", ErrorStatus.Unknown)
    else
        ApiErrorResponse(t.message!!, ErrorStatus.Unknown)

    fun <T> create(response: Response<T>): ApiResponse<T> {
        if (response.isSuccessful) {
            val body: T? = response.body()
            if (body == null && response.code() == 204)
                return ApiEmptyResponse()
            return ApiSuccessResponse(body!!)
        } else {
            when {
                response.code() == 401 -> return ApiErrorResponse(
                    "Unauthorized",
                    ErrorStatus.Unauthorized
                )
                response.code() == 403 -> return ApiErrorResponse(
                    "Forbidden",
                    ErrorStatus.Forbidden
                )
                response.code() == 404 -> return ApiErrorResponse("Not Found", ErrorStatus.NotFound)
            }
        }
        return ApiErrorResponse("Unknown Execption !", ErrorStatus.Unknown)
    }

    class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

    class ApiEmptyResponse<T>() : ApiResponse<T>()

    class ApiErrorResponse<T>(val msg: String, val errorStatus: ErrorStatus) : ApiResponse<T>()

}