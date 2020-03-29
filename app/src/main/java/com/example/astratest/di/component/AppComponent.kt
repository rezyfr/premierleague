package com.example.astratest.di.component

import android.app.Application
import com.example.astratest.App
import com.example.astratest.di.builder.ActivityBuilder
import com.example.astratest.di.module.AppModule
import com.example.astratest.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityBuilder::class,
        AppModule::class, NetworkModule::class]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}