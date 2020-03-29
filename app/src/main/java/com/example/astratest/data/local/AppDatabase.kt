package com.example.astratest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.astratest.data.network.model.Team

@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favDao(): FavDao
}