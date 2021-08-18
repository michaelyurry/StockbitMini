package com.yurry.stockbitmini.data.remote.network

import com.yurry.stockbitmini.data.remote.model.CryptoResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface ApiService {
    @GET("data/top/totaltoptiervolfull")
    suspend fun getTopCrypto(@QueryMap data: Map<String, Any>): CryptoResponse
}