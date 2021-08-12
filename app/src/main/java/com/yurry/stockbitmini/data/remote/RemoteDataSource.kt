package com.yurry.stockbitmini.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.yurry.stockbitmini.data.remote.model.CryptoResponse
import com.yurry.stockbitmini.data.remote.network.ApiResponse
import com.yurry.stockbitmini.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService){
    fun getTopCrypto(page: Int = 1): LiveData<ApiResponse<CryptoResponse>> {
        val config = mutableMapOf<String, Any>(
            "limit" to 10,
            "page" to page,
            "tsym" to "USD"
        )
        return flow {
            try {
                val response = apiService.getTopCrypto(config)
                val cryptoList = response.data
                if (cryptoList.isNullOrEmpty()){
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO).asLiveData()
    }
}