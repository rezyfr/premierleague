package com.example.premierleague.data.network

import com.example.premierleague.data.network.model.ResponseFav
import com.example.premierleague.data.network.model.ResponseKlasemen
import com.example.premierleague.data.network.model.ResponseTopskor
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMain {
    @GET("leagueTable/524")
    fun getKlasemen(): Observable<ResponseKlasemen>

    @GET("topscorers/524")
    fun getTopScorer(): Observable<ResponseTopskor>

    @GET("teams/team/{team_id}")
    fun getFavTeam(
        @Path("team_id") team_id: Int
    ): Observable<ResponseFav>
}