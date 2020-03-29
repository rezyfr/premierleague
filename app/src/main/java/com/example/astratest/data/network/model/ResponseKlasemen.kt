package com.example.astratest.data.network.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class ResponseKlasemen(
    val api: ApiKlasemen
)

data class ApiKlasemen(
    val results: Int,
    val standings: ArrayList<ArrayList<Standing>>
)

@Parcelize
data class Standing(
    val all: All,
    val forme: String,
    val goalsDiff: Int,
    val group: String,
    val lastUpdate: String,
    val logo: String,
    val points: Int,
    val rank: Int,
    val status: String,
    val teamName: String,
    @PrimaryKey val team_id: Int
):Parcelable

@Parcelize
data class All(
    val draw: Int,
    val goalsAgainst: Int,
    val goalsFor: Int,
    val lose: Int,
    val matchsPlayed: Int,
    val win: Int
):Parcelable