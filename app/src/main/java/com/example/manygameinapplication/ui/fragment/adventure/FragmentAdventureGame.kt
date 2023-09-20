package com.example.manygameinapplication.ui.fragment.adventure

import android.content.Context
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cookingguide.base.BaseFragmentWithBinding
import com.example.manygameinapplication.adapter.GameAdapter
import com.example.manygameinapplication.databinding.FragmentAdventureGameBinding
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.ui.webview.WebViewActivity
import com.example.manygameinapplication.utils.Common
import com.example.manygameinapplication.utils.Constant

class FragmentAdventureGame : BaseFragmentWithBinding<FragmentAdventureGameBinding>(
    FragmentAdventureGameBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentAdventureGame()
    }
    private val viewModel: AdventureViewModel by viewModels()
    private val adapter by lazy {
        GameAdapter(
            requireContext(),
            ::onItemClick
        )
    }

    override fun initAction() {
        binding.RcvGameAdventure.apply {
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            adapter = this@FragmentAdventureGame.adapter
        }
    }

    override fun initData() {
        viewModel.getAdventureGame()?.observe(viewLifecycleOwner) {
            binding.ProgressBarLoading.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
            adapter.submitList(it)
        }
    }

    private fun onItemClick(racingModel: GameModel) {
        if (racingModel.linkGame.isNotEmpty()) {
            requireContext().openWebView(racingModel.linkGame)
        }
    }

    private fun Context.openWebView(linkGame: String) {
        Common.openActivitySendData(
            this,
            Constant.linkGame,
            linkGame,
            WebViewActivity::class.java
        )
    }
}