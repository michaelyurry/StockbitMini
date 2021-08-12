package com.yurry.stockbitmini.di

import com.yurry.stockbitmini.domain.usecase.CryptoUseCase
import com.yurry.stockbitmini.domain.usecase.LoginUseCase
import com.yurry.stockbitmini.domain.usecase.LogoutUseCase
import com.yurry.stockbitmini.domain.usecase.ProfileUseCase
import com.yurry.stockbitmini.domain.usecase.interactor.CryptoInteractor
import com.yurry.stockbitmini.domain.usecase.interactor.LoginInteractor
import com.yurry.stockbitmini.domain.usecase.interactor.LogoutInteractor
import com.yurry.stockbitmini.domain.usecase.interactor.ProfileInteractor
import org.koin.dsl.module

private val coinUseCaseModule = module {
    single<CryptoUseCase> {
        CryptoInteractor(get())
    }
}

private val loginUseCaseModule = module {
    single<LoginUseCase> {
        LoginInteractor(get())
    }
}

private val logoutUseCaseModule = module {
    single<LogoutUseCase> {
        LogoutInteractor(get())
    }
}

private val profileUseCaseModule = module {
    single<ProfileUseCase> {
        ProfileInteractor(get())
    }
}

val useCaseModuleList = mutableListOf(
    coinUseCaseModule,
    loginUseCaseModule,
    logoutUseCaseModule,
    profileUseCaseModule
)