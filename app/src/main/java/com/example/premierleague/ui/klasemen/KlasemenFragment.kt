package com.example.premierleague.ui.klasemen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.premierleague.R
import com.example.premierleague.data.network.model.Standing
import com.example.premierleague.ui.MainViewModel
import com.example.premierleague.ui.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_klasemen.*
import kotlinx.android.synthetic.main.progress_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class KlasemenFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance(): KlasemenFragment {
            val fragment = KlasemenFragment()
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
        return inflater.inflate(R.layout.fragment_klasemen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val klasemenAdapter = KlasemenAdapter()
        rv_klasemen.adapter = klasemenAdapter
        
        klasemenAdapter.setOnAdapterInteractionListener(object :
            KlasemenAdapter.OnAdapterInteractionListener {
            override fun itemSelected(standing: Standing) {
                Intent(context, TeamDetailActivity::class.java).also{
                    it.putExtra("team", standing)
                    startActivity(it)
                }
            }
        })

        viewModel.stateKlasemen.observe(this, Observer {
            if (it is MainViewModel.StateKlasemen.ShowKlasemen) {
                val klasemen = it.data.api.standings[0]
                klasemenAdapter.onUpdate(klasemen)
            }
        })
        viewModel.stateLoading.observe(this, Observer {
            if (it == View.VISIBLE) {
                ll_klasemen.visibility = View.GONE
                pd_loading.visibility = it
            } else {
                ll_klasemen.visibility = View.VISIBLE
                pd_loading.visibility = it
            }
        })
        viewModel.loadKlasemen()
    }
}
