package com.yurry.stockbitmini.data.repository

import androidx.lifecycle.LiveData
import com.yurry.stockbitmini.data.local.SharedPrefDataStore
import com.yurry.stockbitmini.data.local.model.ProfileEntity
import com.yurry.stockbitmini.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val sharedPrefDataStore: SharedPrefDataStore): ProfileRepository{
    override fun getProfile(): LiveData<ProfileEntity?> {
        return sharedPrefDataStore.getMyProfile()
    }
}