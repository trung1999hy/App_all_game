package com.example.manygameinapplication.network

import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.utils.Constant
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(Constant.racingUrl)
    fun getRacingData() : Call<ArrayList<GameModel>>

    @GET(Constant.adventureUrl)
    fun getAdventureData() : Call<ArrayList<GameModel>>

    @GET(Constant.arcadeUrl)
    fun getArcadeData() : Call<ArrayList<GameModel>>

    @GET(Constant.puzzleUrl)
    fun getPuzzleData() : Call<ArrayList<GameModel>>

    @GET(Constant.sportsUrl)
    fun getSportsData() : Call<ArrayList<GameModel>>
}