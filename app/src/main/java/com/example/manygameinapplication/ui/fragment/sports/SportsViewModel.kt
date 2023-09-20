package com.example.manygameinapplication.ui.fragment.sports

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.utils.Common

class SportsViewModel : ViewModel() {
    private var sportsGame: LiveData<ArrayList<GameModel>>? = null

    fun getSportsGame(): LiveData<ArrayList<GameModel>>? {
        sportsGame = Common.repository.getSportsGame()
        return sportsGame
    }
}