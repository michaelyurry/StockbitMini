package com.yurry.stockbitmini.ui.logout

import androidx.lifecycle.ViewModel
import com.yurry.stockbitmini.domain.usecase.LogoutUseCase

class LogoutViewModel(private val logoutUseCase: LogoutUseCase): ViewModel() {
    fun logout() {
        logoutUseCase.logout()
    }
}