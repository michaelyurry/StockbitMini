package com.yurry.stockbitmini.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.yurry.stockbitmini.data.local.model.ProfileEntity

class SharedPrefDataStore(private val context: Context, private val gson: Gson) {

    val mainSharePref: SharedPreferences by lazy{context.getSharedPreferences(
        MAIN_SHARED_PREF_DATA_STORE, Context.MODE_PRIVATE)}

    companion object {
        @Volatile
        private var instance: SharedPrefDataStore? = null

        fun getInstance(context: Context): SharedPrefDataStore =
            instance ?: synchronized(this) {
                instance ?: SharedPrefDataStore(context, Gson())
            }
    }

    fun saveData(key: String, value: Any){
        val editor = mainSharePref.edit()
        when(value){
            is Int -> editor.putInt(key, value)
            is String -> editor.putString(key, value)
            is Long -> editor.putLong(key, value)
            is Float -> editor.putFloat(key, value)
            is Boolean -> editor.putBoolean(key, value)
            else -> return
        }
        editor.apply()
    }

    fun saveMyProfile(user: ProfileEntity?){
        if(user == null) return
        mainSharePref.edit().putString(MY_PROFILE, gson.toJson(user)).apply()
    }

    fun clearMyProfile(){
        saveData(MY_PROFILE, "")
    }

    fun getMyProfile(): LiveData<ProfileEntity?>{
        return object : SharedPreferenceLiveData<ProfileEntity?>(mainSharePref, MY_PROFILE, null){
            override fun getValueFromPreferences(key: String?, defValue: ProfileEntity?): ProfileEntity? {
                val dataString = sharedPrefs.getString(key, "")
                if(!dataString.isNullOrEmpty()){
                    return gson.fromJson(dataString, ProfileEntity::class.java)
                }
                return null
            }
        }
    }

    fun getLoginStatus(): String{
        val dataString = mainSharePref.getString(MY_PROFILE, "")
        if(!dataString.isNullOrEmpty()){
            return gson.fromJson(dataString, ProfileEntity::class.java).loginFlag?:""
        }
        return ""
    }

    fun getBooleanLiveData(key: String): LiveData<Boolean>{
        return object : SharedPreferenceLiveData<Boolean>(mainSharePref, key, false){
            override fun getValueFromPreferences(key: String?, defValue: Boolean): Boolean {
                return sharedPrefs.getBoolean(key, defValue)
            }
        }
    }

    fun getBooleanData(key: String): Boolean{
        return mainSharePref.getBoolean(key, false)
    }
}