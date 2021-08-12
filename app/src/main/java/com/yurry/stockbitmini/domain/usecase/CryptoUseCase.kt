package com.yurry.stockbitmini.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.yurry.stockbitmini.data.Resource
import com.yurry.stockbitmini.data.remote.model.CryptoItem

interface CryptoUseCase {
    fun getTopCrypto(): LiveData<Resource<PagedList<CryptoItem>>>
}