package com.yurry.stockbitmini.data.repository

import com.yurry.stockbitmini.data.local.IS_LOGIN
import com.yurry.stockbitmini.data.local.SharedPrefDataStore
import com.yurry.stockbitmini.domain.repository.LogoutRepository

class LogoutRepositoryImpl (private val sharedPrefDataStore: SharedPrefDataStore): LogoutRepository {
    override fun logout() {
        sharedPrefDataStore.clearMyProfile()
        sharedPrefDataStore.saveData(IS_LOGIN, false)
    }
}