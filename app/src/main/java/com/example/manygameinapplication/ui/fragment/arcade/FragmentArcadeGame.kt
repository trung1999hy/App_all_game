package com.example.manygameinapplication.ui.fragment.arcade

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cookingguide.base.BaseFragmentWithBinding
import com.example.manygameinapplication.adapter.GameAdapter
import com.example.manygameinapplication.databinding.FragmentArcadeGameBinding
import com.example.manygameinapplication.model.GameModel
import com.example.manygameinapplication.ui.webview.WebViewActivity
import com.example.manygameinapplication.utils.Common
import com.example.manygameinapplication.utils.Constant

class FragmentArcadeGame : BaseFragmentWithBinding<FragmentArcadeGameBinding>(
    FragmentArcadeGameBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentArcadeGame()

        var rcvGame: RecyclerView? = null

        @SuppressLint("StaticFieldLeak")
        var layoutNoti: LinearLayout? = null

        fun hideGoneLayout(isCheck: Boolean) {
            rcvGame?.visibility = if (!isCheck) View.GONE else View.VISIBLE
            layoutNoti?.visibility = if (!isCheck) View.VISIBLE else View.GONE
        }
    }

    private val viewModel: ArcadeViewModel by viewModels()
    private val adapter by lazy {
        GameAdapter(
            requireContext(),
            ::onItemClick
        )
    }

    override fun initAction() {
        rcvGame = binding.RcvGameArcade
        layoutNoti = binding.LlNoti
        binding.RcvGameArcade.apply {
            layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            adapter = this@FragmentArcadeGame.adapter
        }
    }

    override fun initData() {
        viewModel.getArcadeGame()?.observe(viewLifecycleOwner) {
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