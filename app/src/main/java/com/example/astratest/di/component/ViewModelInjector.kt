package com.example.astratest.di.component

import com.example.astratest.di.module.NetworkModule
import com.example.astratest.ui.MainViewModel
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