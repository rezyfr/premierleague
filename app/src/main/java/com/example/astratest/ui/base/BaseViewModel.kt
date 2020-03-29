package com.example.astratest.ui.base

import androidx.lifecycle.ViewModel
import com.example.astratest.data.network.ApiMain
import com.example.astratest.di.component.DaggerViewModelInjector
import com.example.astratest.di.component.ViewModelInjector
import com.example.astratest.di.module.NetworkModule
import com.example.astratest.ui.MainViewModel
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    private var injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject(){
        when(this){
            is MainViewModel -> injector.inject(this)
        }
    }
}