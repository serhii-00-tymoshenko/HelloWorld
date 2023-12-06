package com.mintokoneko.helloworld.ui.main.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mintokoneko.helloworld.repository.MainRepository
import com.mintokoneko.helloworld.repository.data.Country
import com.mintokoneko.helloworld.repository.data.Credentials

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {
    private val _credentials = MutableLiveData<Credentials>(repository.getCredentials())
    val credentials: LiveData<Credentials> get() = _credentials

    private val _countries = MutableLiveData<List<Country>>(repository.getCountries())
    val countries: LiveData<List<Country>> get() = _countries

    private val _currentCountry = MutableLiveData<Country>()
    val currentCountry: LiveData<Country> get() = _currentCountry

    fun fetchData() {
        val result = repository.fetchData()
        _countries.value = result
    }

    fun setCredentials(credentials: Credentials) {
        repository.setCredentials(credentials)
        _credentials.value = repository.getCredentials()
    }

    fun setCurrentCountry(country: Country) {
        _currentCountry.value = country

    }
}