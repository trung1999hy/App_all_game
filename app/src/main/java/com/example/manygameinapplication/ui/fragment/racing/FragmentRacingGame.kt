package com.example.manygameinapplication.ui.fragment.racing

import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cookingguide.base.BaseFragmentWithBinding
import com.example.manygameinapplication.adapter.GameAdapter
import com.example.manygameinapplication.databinding.FragmentRacingGameBinding
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.ui.webview.WebViewActivity
import com.example.manygameinapplication.utils.Common
import com.example.manygameinapplication.utils.Constant

class FragmentRacingGame : BaseFragmentWithBinding<FragmentRacingGameBinding>(
    FragmentRacingGameBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentRacingGame()
    }

    private val viewModel: RacingViewModel by viewModels()
    private val adapter by lazy {
        GameAdapter(
            requireContext(),
            ::onItemClick
        )
    }

    override fun initAction() {
        binding.RcvGameRacing.apply {
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            adapter = this@FragmentRacingGame.adapter
        }
    }

    override fun initData() {
        viewModel.getRacingGames()?.observe(viewLifecycleOwner) {
            binding.ProgressBarLoading.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
            if (it.isNotEmpty()) adapter.submitList(it)
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