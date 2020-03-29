package com.example.astratest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.astratest.data.network.model.Team
import io.reactivex.Flowable

@Dao
interface FavDao {
    @Query("SELECT * FROM favorite_team")
    fun getFavoriteTeam(): Flowable<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favTeam: Team)
}