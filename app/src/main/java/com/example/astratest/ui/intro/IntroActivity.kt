package com.example.astratest.ui.intro

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.astratest.R
import com.example.astratest.ui.MainViewModel
import com.example.astratest.ui.navigation.NavActivity
import com.example.astratest.util.Constant.PREF_KEY_FIRST_OPEN
import com.example.astratest.util.PreferencesHelper
import com.example.astratest.util.PreferencesHelper.get
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.progress_dialog.*
import java.util.*

class IntroActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        prefs = PreferencesHelper.defaultPrefs(this)
        favObserve()
        viewModel.stateKlasemen.observe(this, androidx.lifecycle.Observer {
            if (it is MainViewModel.StateKlasemen.ShowKlasemen) {
                val intro = it.data.api.standings[0]

                intro.sortWith(Comparator { o1, o2 -> o1!!.teamName.compareTo(o2!!.teamName) })
                val introAdapter = IntroAdapter(intro)
                gv_team.adapter = introAdapter

                introAdapter.setOnAdapterInteractionListener(object :
                    IntroAdapter.OnAdapterInteractionListener {
                    override fun itemSelected(team_id: Int) {
                        btn_lanjut.setOnClickListener {
                            viewModel.getFav(team_id)
                        }
                    }
                })
            }
        })

        viewModel.stateLoading.observe(this, androidx.lifecycle.Observer {
            if (it == View.VISIBLE) {
                pd_loading.visibility = it
            } else {
                pd_loading.visibility = it
            }
        })

        if (prefs[PREF_KEY_FIRST_OPEN]!!) {
            startActivity(Intent(this@IntroActivity, NavActivity::class.java))
            finish()
        } else {
            viewModel.loadKlasemen()
        }
    }

    fun favObserve() {
        viewModel.stateFav.observe(this, androidx.lifecycle.Observer { it ->
            if (it == true) {
                startActivity(Intent(this@IntroActivity, NavActivity::class.java))
            }
        })
    }
}
