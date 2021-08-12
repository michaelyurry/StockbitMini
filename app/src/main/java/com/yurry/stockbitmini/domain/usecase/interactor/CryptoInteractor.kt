package com.yurry.stockbitmini.domain.usecase.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.yurry.stockbitmini.data.Resource
import com.yurry.stockbitmini.data.remote.model.CryptoItem
import com.yurry.stockbitmini.domain.repository.CryptoRepository
import com.yurry.stockbitmini.domain.usecase.CryptoUseCase

class CryptoInteractor (private val repository: CryptoRepository): CryptoUseCase{
    override fun getTopCrypto(): LiveData<Resource<PagedList<CryptoItem>>> {
        return repository.getTopCrypto()
    }
}