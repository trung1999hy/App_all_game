package com.example.manygameinapplication.ui.fragment.puzzle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.utils.Common

class PuzzleViewModel : ViewModel() {
    private var puzzleGame: LiveData<ArrayList<GameModel>>? = null

    fun getPuzzleGame(): LiveData<ArrayList<GameModel>>? {
        puzzleGame = Common.repository.getPuzzleGame()
        return puzzleGame
    }
}