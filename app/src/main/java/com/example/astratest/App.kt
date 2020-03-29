package com.example.astratest

import android.app.Application
import androidx.room.Room
import com.example.astratest.data.local.AppDatabase
import com.example.astratest.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit internal var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    companion object {
        var database: AppDatabase? = null
    }

    override fun androidInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "astratest_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}