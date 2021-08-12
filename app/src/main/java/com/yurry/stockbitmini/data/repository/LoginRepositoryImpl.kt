package com.yurry.stockbitmini.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yurry.stockbitmini.data.local.IS_LOGIN
import com.yurry.stockbitmini.data.local.SharedPrefDataStore
import com.yurry.stockbitmini.data.local.model.ProfileEntity
import com.yurry.stockbitmini.domain.repository.LoginRepository

class LoginRepositoryImpl (private val sharedPrefDataStore: SharedPrefDataStore): LoginRepository{
    override fun login(item: ProfileEntity): LiveData<Boolean> {
        val loginSuccess = MutableLiveData<Boolean>()
        try{
            sharedPrefDataStore.saveMyProfile(item)
            sharedPrefDataStore.saveData(IS_LOGIN, true)
            loginSuccess.value = true
        } catch (e: Exception){
            loginSuccess.value = false
        }
        return loginSuccess    }

    override fun isAlreadyLogin(): LiveData<Boolean> {
        return sharedPrefDataStore.getBooleanLiveData(IS_LOGIN)

    }
}