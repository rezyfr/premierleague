package com.example.premierleague.ui.topskor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.premierleague.R
import com.example.premierleague.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_topskor.*
import kotlinx.android.synthetic.main.progress_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class TopskorFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance(): TopskorFragment {
            val fragment = TopskorFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_topskor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topskorAdapter = TopskorAdapter()
        rv_topskor.adapter = topskorAdapter
        viewModel.stateTopskor.observe(this, Observer {
            if (it is MainViewModel.StateTopskor.ShowTopskor) {
                val topskor = it.data.api.topscorers
                topskorAdapter.onUpdate(topskor)
            }
        })
        viewModel.stateLoading.observe(this, Observer {
            if (it == View.VISIBLE) {
                ll_topskor.visibility = View.GONE
                pd_loading.visibility = it
            } else {
                ll_topskor.visibility = View.VISIBLE
                pd_loading.visibility = it
            }
        })
        viewModel.loadTopskor()
    }

}
