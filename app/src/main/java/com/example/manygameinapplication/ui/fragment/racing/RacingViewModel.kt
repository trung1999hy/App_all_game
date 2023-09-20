package com.example.manygameinapplication.ui.fragment.racing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.utils.Common

class RacingViewModel : ViewModel() {
    private var racingGames: LiveData<ArrayList<GameModel>>? = null

    fun getRacingGames(): LiveData<ArrayList<GameModel>>? {
        racingGames = Common.repository.getRacingGame()
        return racingGames
    }
}