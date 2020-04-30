package com.example.premierleague.data.network.model

data class ResponseTeamDetail(
    val api: ApiTeam
)

data class ApiTeam(
    val results: Int,
    val statistics: Statistics
)

data class Statistics(
    val matchs: Matchs
)

data class Matchs(
    val draws: Draws,
    val loses: Loses,
    val matchsPlayed: MatchsPlayed,
    val wins: Wins
)

data class Draws(
    val away: Int,
    val home: Int,
    val total: Int
)

data class Loses(
    val away: Int,
    val home: Int,
    val total: Int
)

data class Wins(
    val away: Int,
    val home: Int,
    val total: Int
)

data class MatchsPlayed(
    val away: Int,
    val home: Int,
    val total: Int
)