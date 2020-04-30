package com.example.premierleague.ui.teamdetail

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.premierleague.R
import com.example.premierleague.data.network.model.Standing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class TeamDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_white)
        supportActionBar!!.title = ""
        toolbar_title.text = "Statistics"

        val team = intent.getParcelableExtra<Standing>("team")

        val pieChartData = ArrayList<Entry>()

        team?.let { stat ->
            pieChartData.add(Entry(stat.all.win.toFloat(), 0))
            pieChartData.add(Entry(stat.all.lose.toFloat(), 1))
            pieChartData.add(Entry(stat.all.draw.toFloat(), 2))
            tv_position.text = stat.rank.toString() + "."
            tv_club.text = stat.teamName
            Glide.with(this)
                .load(stat.logo)
                .into(iv_logo)
            stat.forme.let {
                val m1 = it.substring(0)
                val m2 = it.substring(1)
                val m3 = it.substring(2)
                val m4 = it.substring(3)
                val m5 = it.substring(4)

                setFiveMatchView(m1, iv_m1)
                setFiveMatchView(m2, iv_m2)
                setFiveMatchView(m3, iv_m3)
                setFiveMatchView(m4, iv_m4)
                setFiveMatchView(m5, iv_m5)
            }
        }

        val dataSet = PieDataSet(pieChartData, "")
        dataSet.valueTextSize = 14F
        dataSet.valueTextColor = Color.WHITE
        dataSet.setValueFormatter {
            it.toInt().toString()
        }

        val sectionName = arrayListOf("Win", "Lose", "Draw")
        val data = PieData(sectionName, dataSet)
        pie_chart.data = data
        pie_chart.setDescription("")
        dataSet.colors = arrayListOf(
            Color.parseColor("#7dd920"),
            Color.parseColor("#d94f20"),
            Color.parseColor("#20d9d9")
        )

        pie_chart.animateXY(500, 500)
    }

    private fun setFiveMatchView(result: String, imgView: ImageView){
        when (result) {
            "W" -> imgView.setImageResource(R.drawable.ic_win)
            "D" -> imgView.setImageResource(R.drawable.ic_draw)
            else -> imgView.setImageResource(R.drawable.ic_lose)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
