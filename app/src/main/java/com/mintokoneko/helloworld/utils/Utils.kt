package com.mintokoneko.helloworld.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mintokoneko.helloworld.repository.MainRepository
import com.mintokoneko.helloworld.ui.main.viewmodels.MainViewModel
import com.mintokoneko.helloworld.ui.main.viewmodels.MainViewModelFactory

fun getViewModel(owner: ViewModelStoreOwner, context: Context): MainViewModel =
    ViewModelProvider(
        owner, MainViewModelFactory(MainRepository(context))
    )[MainViewModel::class.java]
