package com.yurry.stockbitmini.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurry.stockbitmini.Util
import com.yurry.stockbitmini.data.local.model.ProfileEntity
import com.yurry.stockbitmini.domain.usecase.LoginUseCase

class LoginViewModel (private val loginUseCase: LoginUseCase): ViewModel() {

    enum class Validation{
        USERNAME_INVALID,
        PASSWORD_INVALID,
        NONE
    }

    val formValidation = MutableLiveData<Validation>()

    init {
        formValidation.value = Validation.NONE
    }

    fun login(profileItem: ProfileEntity): LiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>()
        doFormValidation(profileItem).also {
            if(it){
                return loginUseCase.login(profileItem)
            } else{
                isSuccess.value = false
            }
        }
        return isSuccess
    }

    val isUserAlreadyLogin by lazy { loginUseCase.isAlreadyLogin() }

    private fun doFormValidation(profileItem: ProfileEntity): Boolean{
        val isUsernameValid = Util.isUserNameValid(profileItem.userName)
        if(!isUsernameValid) formValidation.value = Validation.USERNAME_INVALID
        val isPasswordValid = Util.isUserPasswordValid(profileItem.password)
        if(!isPasswordValid) formValidation.value = Validation.PASSWORD_INVALID
        return isUsernameValid && isPasswordValid
    }

}