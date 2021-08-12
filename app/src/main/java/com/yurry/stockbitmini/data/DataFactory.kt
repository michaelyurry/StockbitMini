package com.yurry.stockbitmini.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class DataFactory <T>(private val data: MutableList<T> = mutableListOf(),
                      private val callBackListener: CallBackListener<T>) : DataSource.Factory<Int, T>() {
    private val liveData = MutableLiveData<PageKeyedDataSource<Int, T>>()

    override fun create(): DataSource<Int, T> {
        val dataSource = DataSources(data, callBackListener)
        liveData.postValue(dataSource)
        return dataSource
    }

    fun updateData(data: MutableList<T>){
        this.data.addAll(data)
        val dataSource = DataSources(this.data, callBackListener)
        liveData.postValue(dataSource)
    }

    class DataSources<T>(private val data: MutableList<T>,
                                  private val callBackListener: CallBackListener<T>) : PageKeyedDataSource<Int, T>() {

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
            callback.onResult(data, null, 1)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            callBackListener.callBackAfter(params, callback)
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            callBackListener.callBackBefore(params, callback)
        }
    }

    interface CallBackListener<T>{
        fun callBackAfter(params: PageKeyedDataSource.LoadParams<Int>,
                          callback: PageKeyedDataSource.LoadCallback<Int, T>)
        fun callBackBefore(params: PageKeyedDataSource.LoadParams<Int>,
                           callback: PageKeyedDataSource.LoadCallback<Int, T>)
    }
}