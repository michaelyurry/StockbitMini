package com.yurry.stockbitmini

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import java.text.DecimalFormat

object Util {
    private const val PASSWORD_MINIMUM_LENGTH = 6

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus?.windowToken?.apply {
            inputMethodManager.hideSoftInputFromWindow(this, 0)
        }
    }

    fun isUserNameValid(username: String?) = !username.isNullOrEmpty()

    fun isUserPasswordValid(password: String?) =  password?.length?:0 >= PASSWORD_MINIMUM_LENGTH


    fun printDecimalFormat(double: Double): String{
        return DecimalFormat("##.###").format(double).replace(".",",")
    }
}