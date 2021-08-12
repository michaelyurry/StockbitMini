package com.yurry.stockbitmini.domain.repository

import androidx.lifecycle.LiveData
import com.yurry.stockbitmini.data.local.model.ProfileEntity

interface ProfileRepository {
    fun getProfile(): LiveData<ProfileEntity?>
}