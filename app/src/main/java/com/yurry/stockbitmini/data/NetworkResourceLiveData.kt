package com.yurry.stockbitmini.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.yurry.stockbitmini.data.remote.network.ApiResponse

abstract class NetworkResourceLiveData<ResultType, RequestType>(private val mMyExecutors: MyExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        fetchFromNetwork()
    }

    protected open fun onFetchFailed(message: String, throwable: Throwable) {}

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun convertToResultType(remoteData: RequestType): LiveData<ResultType>

    fun fetchFromNetwork() {
        val apiResponse = createCall()
        result.value = Resource.Loading()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiResponse.Success -> mMyExecutors.diskIO().execute {
                    mMyExecutors.mainThread().execute {
                        result.addSource(convertToResultType(response.data)) { newData ->
                            result.value = Resource.Success(newData)
                        }
                    }
                }

                is ApiResponse.Empty -> mMyExecutors.mainThread().execute {
                    result.value = Resource.Empty()
                }

                is ApiResponse.Error -> {
                    onFetchFailed(response.errorException.message?:"", response.errorException)
                    mMyExecutors.mainThread().execute {
                        result.value = Resource.Error(response.errorException.message ?: "",
                            response.errorException)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result

    fun asMutableLiveData(): MutableLiveData<Resource<ResultType>> = result
}