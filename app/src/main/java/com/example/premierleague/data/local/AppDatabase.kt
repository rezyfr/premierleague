package com.example.premierleague.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.premierleague.data.network.model.Team

@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favDao(): FavDao
}