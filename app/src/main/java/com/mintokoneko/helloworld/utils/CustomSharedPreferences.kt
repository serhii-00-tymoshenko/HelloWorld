package com.mintokoneko.helloworld.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class CustomSharedPreferences(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    val gson = Gson()

    inline fun <reified T> getData(name: String): T {
        val jsonData = sharedPreferences.getString(name, null)
        return gson.fromJson(jsonData, T::class.java)
    }

    fun <T> setData(name: String, data: T) {
        val jsonData = gson.toJson(data)
        sharedPreferences
            .edit()
            .putString(name, jsonData)
            .apply()
    }
}