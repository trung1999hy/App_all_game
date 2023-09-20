package com.example.manygameinapplication.ui.fragment.arcade

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.utils.Common

class ArcadeViewModel : ViewModel() {
    private var arcadeGame: LiveData<ArrayList<GameModel>>? = null

    fun getArcadeGame(): LiveData<ArrayList<GameModel>>? {
        arcadeGame = Common.repository.getArcadeGame()
        return arcadeGame
    }
}