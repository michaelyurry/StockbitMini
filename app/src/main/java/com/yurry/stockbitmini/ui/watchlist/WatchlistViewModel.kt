package com.yurry.stockbitmini.ui.watchlist

import androidx.lifecycle.ViewModel
import com.yurry.stockbitmini.domain.usecase.CryptoUseCase
import com.yurry.stockbitmini.ui.RefreshLiveData

class WatchlistViewModel (private val useCase: CryptoUseCase): ViewModel() {
    val topCrypto by lazy { RefreshLiveData{ useCase.getTopCrypto()} }
}