package com.example.astratest.ui.navigation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.astratest.R
import com.example.astratest.ui.favorite.FavFragment
import com.example.astratest.ui.klasemen.KlasemenFragment
import com.example.astratest.ui.topskor.TopskorFragment
import com.example.astratest.util.Constant.PREF_KEY_FIRST_OPEN
import com.example.astratest.util.PreferencesHelper
import com.example.astratest.util.PreferencesHelper.set
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_nav.*
import kotlinx.android.synthetic.main.toolbar.*

class NavActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = KlasemenFragment.newInstance()
        addFragment(fragment)

        prefs = PreferencesHelper.defaultPrefs(this)
        prefs[PREF_KEY_FIRST_OPEN] = true

        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        toolbar_title.text = "Premier League"
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_klasemen -> {
                    val fragment = KlasemenFragment.newInstance()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_statistik -> {
                    val fragment = TopskorFragment.newInstance()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_fav -> {
                    val fragment = FavFragment()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
