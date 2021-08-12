package com.yurry.stockbitmini.domain.usecase

import androidx.lifecycle.LiveData
import com.yurry.stockbitmini.data.local.model.ProfileEntity

interface LoginUseCase {
    fun isAlreadyLogin(): LiveData<Boolean>
    fun login(item: ProfileEntity): LiveData<Boolean>
}