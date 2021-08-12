package com.yurry.stockbitmini.domain.repository

import androidx.lifecycle.LiveData
import com.yurry.stockbitmini.data.local.model.ProfileEntity

interface LoginRepository {
    fun login(item: ProfileEntity): LiveData<Boolean>
    fun isAlreadyLogin(): LiveData<Boolean>
}