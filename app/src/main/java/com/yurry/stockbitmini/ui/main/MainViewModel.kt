package com.yurry.stockbitmini.ui.main

import androidx.lifecycle.ViewModel
import com.yurry.stockbitmini.domain.usecase.CryptoUseCase
import com.yurry.stockbitmini.domain.usecase.ProfileUseCase
import com.yurry.stockbitmini.ui.RefreshLiveData

class MainViewModel (private val profileUseCase: ProfileUseCase): ViewModel() {
    val profile by lazy { profileUseCase.getProfile() }
}