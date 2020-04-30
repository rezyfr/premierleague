package com.example.premierleague.ui.klasemen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.premierleague.R
import com.example.premierleague.data.network.model.Standing
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_klasemen_layout.view.*

class KlasemenAdapter : RecyclerView.Adapter<KlasemenAdapter.ItemViewHolder>() {

    val listItem: MutableList<Standing> = mutableListOf()

    private var klasemenClickListener: OnAdapterInteractionListener? = null

    fun setOnAdapterInteractionListener(listener: OnAdapterInteractionListener) {
        klasemenClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_klasemen_layout, parent, false)
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
        fun bindItem(data: Standing) {
            containerView.let {
                it.tv_position.text = data.rank.toString()
                it.tv_club.text = data.teamName
                it.tv_play.text = data.all.matchsPlayed.toString()
                it.tv_goaldiff.text = data.goalsDiff.toString()
                it.tv_poin.text = data.points.toString()

                Glide.with(ctx)
                    .load(data.logo)
                    .into(it.img_club)

                Glide.with(ctx)
                    .load(
                        when (data.status) {
                            "up" -> R.drawable.ic_up
                            "down" -> R.drawable.ic_down
                            else -> R.drawable.ic_none
                        }
                    ).into(it.img_status)

                it.kl_container.setOnClickListener {
                    klasemenClickListener?.itemSelected(data)
                }
            }
        }
    }

    interface OnAdapterInteractionListener {
        fun itemSelected(standing: Standing)
    }

    fun onUpdate(data: ArrayList<Standing>) {
        listItem.clear()
        listItem.addAll(data)
        notifyDataSetChanged()
    }
}