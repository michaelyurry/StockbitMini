package com.yurry.stockbitmini.di

import com.yurry.stockbitmini.data.MyExecutors
import com.yurry.stockbitmini.data.local.SharedPrefDataStore
import com.yurry.stockbitmini.data.remote.RemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(get()) }
}

val appExecutorModule = module {
    single { MyExecutors() }
}

val sharedPrefModule = module {
    single {
        SharedPrefDataStore.getInstance(androidContext())
    }
}

val dataSourceModuleList = mutableListOf(
    remoteDataSourceModule,
    appExecutorModule,
    sharedPrefModule
)