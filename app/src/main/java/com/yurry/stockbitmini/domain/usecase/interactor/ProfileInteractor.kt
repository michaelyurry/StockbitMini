package com.yurry.stockbitmini.domain.usecase.interactor

import androidx.lifecycle.LiveData
import com.yurry.stockbitmini.data.local.model.ProfileEntity
import com.yurry.stockbitmini.domain.repository.ProfileRepository
import com.yurry.stockbitmini.domain.usecase.ProfileUseCase

class ProfileInteractor(private val repository: ProfileRepository): ProfileUseCase {
    override fun getProfile(): LiveData<ProfileEntity?> {
        return repository.getProfile()
    }
}