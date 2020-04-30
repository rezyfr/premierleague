package com.example.premierleague.di.component

import android.app.Application
import com.example.premierleague.App
import com.example.premierleague.di.builder.ActivityBuilder
import com.example.premierleague.di.module.AppModule
import com.example.premierleague.di.module.NetworkModule
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