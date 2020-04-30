package com.example.premierleague.ui.base

import androidx.lifecycle.ViewModel
import com.example.premierleague.data.network.ApiMain
import com.example.premierleague.di.component.DaggerViewModelInjector
import com.example.premierleague.di.component.ViewModelInjector
import com.example.premierleague.di.module.NetworkModule
import com.example.premierleague.ui.MainViewModel
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