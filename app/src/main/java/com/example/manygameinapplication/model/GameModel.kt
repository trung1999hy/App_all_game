package com.example.manygameinapplication.model

import com.google.gson.annotations.SerializedName

data class GameModel(
    @SerializedName("cid")
    val cid : Int,
    @SerializedName("link_game")
    val linkGame: String,
    @SerializedName("logo_game")
    val logoGame: String
)