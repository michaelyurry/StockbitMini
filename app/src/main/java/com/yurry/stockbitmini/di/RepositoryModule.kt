package com.yurry.stockbitmini.di

import com.yurry.stockbitmini.data.repository.CryptoRepositoryImpl
import com.yurry.stockbitmini.data.repository.LoginRepositoryImpl
import com.yurry.stockbitmini.data.repository.LogoutRepositoryImpl
import com.yurry.stockbitmini.data.repository.ProfileRepositoryImpl
import com.yurry.stockbitmini.domain.repository.CryptoRepository
import com.yurry.stockbitmini.domain.repository.LoginRepository
import com.yurry.stockbitmini.domain.repository.LogoutRepository
import com.yurry.stockbitmini.domain.repository.ProfileRepository
import org.koin.dsl.module

private val cryptoRepositoryModule = module {
    single<CryptoRepository> { CryptoRepositoryImpl(get(), get()) }
}

private val loginRepositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}

private val logoutRepositoryModule = module {
    single<LogoutRepository> { LogoutRepositoryImpl(get()) }
}

private val profileRepositoryModule = module {
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
}

val repositoryModuleList = mutableListOf(
    cryptoRepositoryModule,
    loginRepositoryModule,
    logoutRepositoryModule,
    profileRepositoryModule
)