package com.yurry.stockbitmini.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.yurry.stockbitmini.data.Resource
import com.yurry.stockbitmini.data.remote.model.CryptoItem

interface CryptoRepository {
    fun getTopCrypto(): LiveData<Resource<PagedList<CryptoItem>>>
}