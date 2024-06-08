package com.freelancersworld.data.remote.source.impl

import com.freelancersworld.data.models.APIError
import com.freelancersworld.data.remote.utils.DataState
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Flow<DataState<T>> {
        return flow<DataState<T>> {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(DataState.Success(body))
                else {
                    val apiError: APIError =
                        Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                    emit(DataState.Error(apiError))
                }
            } else {
                val apiError: APIError =
                    Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                emit(DataState.Error(apiError))
            }
        }.catch {
                emit(DataState.Error(APIError(-1, it.message ?: it.toString())))
        }.onStart { emit(DataState.Loading()) }.flowOn(Dispatchers.IO)
    }
}