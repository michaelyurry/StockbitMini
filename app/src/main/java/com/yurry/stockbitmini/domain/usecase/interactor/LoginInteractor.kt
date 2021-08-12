package com.yurry.stockbitmini.domain.usecase.interactor

import androidx.lifecycle.LiveData
import com.yurry.stockbitmini.data.local.model.ProfileEntity
import com.yurry.stockbitmini.domain.repository.LoginRepository
import com.yurry.stockbitmini.domain.usecase.LoginUseCase

class LoginInteractor(private val repository: LoginRepository): LoginUseCase {
    override fun isAlreadyLogin(): LiveData<Boolean> {
        return repository.isAlreadyLogin()
    }

    override fun login(item: ProfileEntity): LiveData<Boolean> {
        return repository.login(item)
    }
}