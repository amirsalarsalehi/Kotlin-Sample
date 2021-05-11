package com.lorevantonio.kotlinofflinefirst.app.util

import kotlinx.coroutines.flow.*

inline fun <Result, Request> netWorkBoundResource(
    crossinline query: () -> Flow<Result>,
    crossinline fetch: suspend () -> ApiResponse<Request>,
    crossinline saveFetchResult: suspend (Request) -> Unit,
    crossinline shouldFetch: (Result) -> Boolean = { true }
) = flow {

    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            when (val apiResponse = fetch()) {
                is ApiResponse.ApiSuccessResponse -> {
                    saveFetchResult(apiResponse.body)
                    query().map { Resource.Success(it) }
                }
                is ApiResponse.ApiErrorResponse -> {
                    query().map { Resource.Error(it, apiResponse.msg) }
                }
                is ApiResponse.ApiEmptyResponse -> {
                    query().map { Resource.Success(it) }
                }
                else -> {
                    query().map { Resource.Success(it) }
                }
            }
        } catch (t: Throwable) {
            query().map { Resource.Error(it, ApiException.create(t).msg) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}