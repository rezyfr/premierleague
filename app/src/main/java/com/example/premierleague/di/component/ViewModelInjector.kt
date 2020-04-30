package com.example.premierleague.di.component

import com.example.premierleague.di.module.NetworkModule
import com.example.premierleague.ui.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector{

    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}