package com.example.manygameinapplication.ui.fragment.adventure

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.utils.Common

class AdventureViewModel : ViewModel() {
    private var adventureGame: LiveData<ArrayList<GameModel>>? = null

    fun getAdventureGame(): LiveData<ArrayList<GameModel>>? {
        adventureGame = Common.repository.getAdventureGame()
        return adventureGame
    }
}