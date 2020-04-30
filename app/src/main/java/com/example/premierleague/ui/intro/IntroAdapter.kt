package com.example.premierleague.ui.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.premierleague.R
import com.example.premierleague.data.network.model.Standing
import kotlinx.android.synthetic.main.item_intro_layout.view.*

class IntroAdapter(var listItem: MutableList<Standing> = mutableListOf()) : BaseAdapter() {

    private var introClickListener: OnAdapterInteractionListener? = null

    fun setOnAdapterInteractionListener(listener: OnAdapterInteractionListener) {
        introClickListener = listener
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listItem.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflator = parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val introView = inflator.inflate(R.layout.item_intro_layout, null)

        introView.tv_club.text = listItem[position].teamName
        Glide.with(parent.context)
            .load(listItem[position].logo)
            .into(introView.iv_club)
        introView.item_intro.setOnClickListener {
            introClickListener?.itemSelected(listItem[position].team_id)
            introView.item_intro.setBackgroundColor(introView.resources.getColor(R.color.colorAccentLight))
        }
        return introView
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItem(position: Int): Any {
        return listItem[position]
    }

    interface OnAdapterInteractionListener {
        fun itemSelected(team_id: Int)
    }

}