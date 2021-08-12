package com.yurry.stockbitmini.domain.usecase.interactor

import com.yurry.stockbitmini.domain.repository.LogoutRepository
import com.yurry.stockbitmini.domain.usecase.LogoutUseCase

class LogoutInteractor(private val repository: LogoutRepository): LogoutUseCase {
    override fun logout() {
        return repository.logout()
    }
}