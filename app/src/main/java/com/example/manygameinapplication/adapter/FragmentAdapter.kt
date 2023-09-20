package com.example.manygameinapplication.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.manygameinapplication.ui.fragment.racing.FragmentRacingGame

class FragmentAdapter(fragment: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragment, lifecycle) {

     private var mList: ArrayList<Fragment> = arrayListOf()

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mList[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list : ArrayList<Fragment>) {
        this.mList = list
        notifyDataSetChanged()
    }

    fun hiddenItem(position: Int) {
        mList[position] = FragmentRacingGame.newInstance()
        notifyItemChanged(position)
    }
}