package com.example.manygameinapplication.ui.fragment.puzzle

import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cookingguide.base.BaseFragmentWithBinding
import com.example.manygameinapplication.adapter.GameAdapter
import com.example.manygameinapplication.databinding.FragmentPuzzleGameBinding
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.ui.webview.WebViewActivity
import com.example.manygameinapplication.utils.Common
import com.example.manygameinapplication.utils.Constant

class FragmentPuzzleGame : BaseFragmentWithBinding<FragmentPuzzleGameBinding>(
    FragmentPuzzleGameBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentPuzzleGame()
    }

    private val viewModel: PuzzleViewModel by viewModels()
    private val adapter by lazy {
        GameAdapter(
            requireContext(),
            ::onItemClick
        )
    }

    override fun initAction() {
        binding.RcvGamePuzzle.apply {
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            adapter = this@FragmentPuzzleGame.adapter
        }
    }

    override fun initData() {
        viewModel.getPuzzleGame()?.observe(viewLifecycleOwner) {
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