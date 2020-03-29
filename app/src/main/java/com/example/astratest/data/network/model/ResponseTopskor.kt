package com.example.astratest.data.network.model

data class ResponseTopskor(
    val api: ApiTopSkor
)

data class ApiTopSkor(
    val results: Int,
    val topscorers: ArrayList<Topscorer>
)

data class Topscorer(
    val firstname: String,
    val games: Games,
    val goals: Goals,
    val lastname: String,
    val player_id: Int,
    val player_name: String,
    val team_id: Int,
    val team_name: String
)

data class Goals(
    val total: Int
)

data class Games(
    val appearences: Int,
    val minutes_played: Int
)