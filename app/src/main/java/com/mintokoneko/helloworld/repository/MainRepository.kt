package com.mintokoneko.helloworld.repository

import android.content.Context
import com.mintokoneko.helloworld.repository.data.Country
import com.mintokoneko.helloworld.repository.data.Credentials
import com.mintokoneko.helloworld.utils.COUNTRIES_NAME
import com.mintokoneko.helloworld.utils.CREDENTIALS_NAME
import com.mintokoneko.helloworld.utils.CustomSharedPreferences

class MainRepository(context: Context) {
    private val customSharedPreferences = CustomSharedPreferences(context)

    fun fetchData(): List<Country> = listOf(
        Country("A", 0),
        Country("B", 1),
        Country("C", 2),
        Country("D", 3),
        Country("E", 4),
        Country("F", 5),
        Country("G", 6),
        Country("H", 7),
        Country("I", 8),
        Country("J", 9),
    )

    fun setCredentials(credentials: Credentials) {
        customSharedPreferences.setData(CREDENTIALS_NAME, credentials)
    }

    fun getCredentials() : Credentials =
        customSharedPreferences.getData(CREDENTIALS_NAME)

    fun setCountries(countries: List<Country>) {
        customSharedPreferences.setData(COUNTRIES_NAME, countries)
    }

    fun getCountries() : List<Country> =
        customSharedPreferences.getData(COUNTRIES_NAME)
}