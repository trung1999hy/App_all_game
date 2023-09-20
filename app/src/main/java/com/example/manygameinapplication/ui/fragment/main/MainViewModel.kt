package com.example.manygameinapplication.ui.fragment.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.manygameinapplication.R
import com.example.manygameinapplication.ui.fragment.adventure.FragmentAdventureGame
import com.example.manygameinapplication.ui.fragment.arcade.FragmentArcadeGame
import com.example.manygameinapplication.ui.fragment.puzzle.FragmentPuzzleGame
import com.example.manygameinapplication.ui.fragment.racing.FragmentRacingGame
import com.example.manygameinapplication.ui.fragment.sports.FragmentSportsGame

class MainViewModel : ViewModel() {
    private val _listFragment = MutableLiveData<ArrayList<Fragment>>()
    val listFragment: LiveData<ArrayList<Fragment>> = _listFragment

    private val _arrTitleTab = MutableLiveData<Array<String>>()
    val arrTitleTab: LiveData<Array<String>> = _arrTitleTab

    private val _arrIconTab = MutableLiveData<Array<Int>>()
    var arrIconTab: LiveData<Array<Int>> = _arrIconTab

    init {
        val fragmentList = arrayListOf<Fragment>(
            FragmentRacingGame.newInstance(),
            FragmentAdventureGame.newInstance(),
            FragmentArcadeGame.newInstance(),
            FragmentPuzzleGame.newInstance(),
            FragmentSportsGame.newInstance(),
        )
        _listFragment.value = fragmentList

        _arrTitleTab.value = arrayOf("Racing", "Adventure", "Arcade", "Puzzle", "Sports")
        _arrIconTab.value = arrayOf(
            R.drawable.racing,
            R.drawable.adventurer,
            R.drawable.arcade,
            R.drawable.puzzle,
            R.drawable.sports
        )
    }

    fun updateFragmentList(newList: ArrayList<Fragment>) {
        _listFragment.value = newList
    }

    fun updateGameGenres(newGenres: Array<String>) {
        _arrTitleTab.value = newGenres
    }
}