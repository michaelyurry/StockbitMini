package com.yurry.stockbitmini.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.yurry.stockbitmini.data.DataFactory
import com.yurry.stockbitmini.data.MyExecutors
import com.yurry.stockbitmini.data.NetworkResourceLiveData
import com.yurry.stockbitmini.data.Resource
import com.yurry.stockbitmini.data.remote.RemoteDataSource
import com.yurry.stockbitmini.data.remote.model.CryptoItem
import com.yurry.stockbitmini.data.remote.model.CryptoResponse
import com.yurry.stockbitmini.data.remote.network.ApiResponse
import com.yurry.stockbitmini.domain.repository.CryptoRepository

class CryptoRepositoryImpl(private val remoteDataSource: RemoteDataSource,
                           private val executors: MyExecutors): CryptoRepository {
    override fun getTopCrypto(): LiveData<Resource<PagedList<CryptoItem>>> {
        var nextPage = 1
        var networkBound: NetworkResourceLiveData<PagedList<CryptoItem>, CryptoResponse>?= null
        val dataFactory = DataFactory(mutableListOf(),
            object : DataFactory.CallBackListener<CryptoItem>{
                override fun callBackAfter(
                    params: PageKeyedDataSource.LoadParams<Int>,
                    callback: PageKeyedDataSource.LoadCallback<Int, CryptoItem>
                ) {
                    executors.mainThread().execute {
                        if(nextPage <= 5){
                            networkBound?.fetchFromNetwork()
                        }
                    }
                }

                override fun callBackBefore(
                    params: PageKeyedDataSource.LoadParams<Int>,
                    callback: PageKeyedDataSource.LoadCallback<Int, CryptoItem>
                ) {

                }

            })

        networkBound = object :NetworkResourceLiveData<PagedList<CryptoItem>, CryptoResponse>(executors){
            override fun createCall(): LiveData<ApiResponse<CryptoResponse>> {
                return remoteDataSource.getTopCrypto(nextPage)
            }

            override fun convertToResultType(remoteData: CryptoResponse): LiveData<PagedList<CryptoItem>> {
                nextPage += 1
                dataFactory.updateData(remoteData.data?.toMutableList()?: mutableListOf())
                return LivePagedListBuilder(dataFactory, PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()).build()
            }
        }

        return networkBound.asLiveData()    }
}