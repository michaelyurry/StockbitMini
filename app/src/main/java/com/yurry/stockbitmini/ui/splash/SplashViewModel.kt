package com.yurry.stockbitmini.ui.splash

import androidx.lifecycle.ViewModel
import com.yurry.stockbitmini.domain.usecase.LoginUseCase

class SplashViewModel(private val loginUseCase: LoginUseCase): ViewModel(){
    val isUserAlreadyLogin by lazy { loginUseCase.isAlreadyLogin() }
}