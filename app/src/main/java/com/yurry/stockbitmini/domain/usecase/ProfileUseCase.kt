package com.yurry.stockbitmini.domain.usecase

import androidx.lifecycle.LiveData
import com.yurry.stockbitmini.data.local.model.ProfileEntity

interface ProfileUseCase {
    fun getProfile(): LiveData<ProfileEntity?>
}