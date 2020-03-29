package com.example.astratest.ui.topskor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astratest.R
import com.example.astratest.data.network.model.Topscorer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_topskor_layout.view.*

class TopskorAdapter : RecyclerView.Adapter<TopskorAdapter.ItemViewHolder>() {

    val listItem: MutableList<Topscorer> = mutableListOf()

    private var klasemenClickListener: OnAdapterInteractionListener? = null

    fun setOnAdapterInteractionListener(listener: OnAdapterInteractionListener) {
        klasemenClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_topskor_layout, parent, false)
        return ItemViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int = listItem.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(listItem[position])
    }

    inner class ItemViewHolder(override val containerView: View, private val ctx: Context) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(data: Topscorer) {
            containerView.let {
                it.tv_position.text = (adapterPosition + 1).toString()
                it.tv_player.text = data.player_name
                it.tv_club.text = data.team_name
                val minPlayed = data.games.minutes_played.toString()
                it.tv_play.text =  "$minPlayed minutes"
                it.tv_gol.text = data.goals.total.toString()
            }
            
        }
    }

    interface OnAdapterInteractionListener {
        fun itemSelected(img: String, title: String, author: String, content: String)
    }

    fun onUpdate(data: ArrayList<Topscorer>) {
        listItem.clear()
        listItem.addAll(data)
        notifyDataSetChanged()
    }


}