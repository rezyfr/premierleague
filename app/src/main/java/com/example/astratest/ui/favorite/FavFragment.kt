package com.example.astratest.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.astratest.R
import com.example.astratest.ui.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_fav.*

/**
 * A simple [Fragment] subclass.
 */
class FavFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance(): FavFragment {
            val fragment = FavFragment()
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
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadFav()?.let {
            viewModel.loadFav()!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { team ->
                        tv_club.text = team[0].name
                        tv_tahun.text = team[0].founded.toString()
                        tv_kota.text = team[0].venue_city
                        tv_stadion.text = team[0].venue_name
                        tv_kapasitas.text = team[0].venue_capacity.toString()
                        tv_alamat.text = team[0].venue_address
                        Glide.with(this)
                            .load(team[0].logo)
                            .into(iv_club)
                    },
                    {error -> Log.e("FavFragment: ", error.message)}
                )
        }
    }
}
