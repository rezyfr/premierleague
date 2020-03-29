package com.example.astratest.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ResponseFav(
    val api: ApiFav
)

data class ApiFav(
    val results: Int,
    val teams: ArrayList<Team>
)

@Entity(tableName = "favorite_team")
data class Team(
    val code: Int,
    val country: String,
    val founded: Int,
    val logo: String,
    var name: String,
    @PrimaryKey
    val team_id: Int,
    val venue_address: String,
    val venue_capacity: Int,
    val venue_city: String,
    val venue_name: String
)