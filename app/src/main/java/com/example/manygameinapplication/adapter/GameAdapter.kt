package com.example.manygameinapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.manygameinapplication.R
import com.example.manygameinapplication.base.recycleview.BaseRecyclerView
import com.example.manygameinapplication.base.recycleview.BaseViewHolder
import com.example.manygameinapplication.databinding.ItemRcvGameBinding
import com.example.manygameinapplication.model.GameModel

class GameAdapter(private val context: Context, val onClickItem: (GameModel) -> Unit) :
    BaseRecyclerView<GameModel, GameAdapter.ViewHolder>() {

    private var mList: MutableList<GameModel> = ArrayList()

    inner class ViewHolder(private val binding: ItemRcvGameBinding) :
        BaseViewHolder<GameModel>(binding) {
        override fun bindViewHolder(data: GameModel) {
            itemView.setOnClickListener { onClickItem.invoke(data) }
            Glide.with(context).load(data.logoGame).into(binding.LogoGame)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<GameModel>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun getListItem(): MutableList<GameModel> = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRcvGameBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mList[position])
        setAnimation(context, holder.itemView, R.anim.rcv_fade_in)
    }
}