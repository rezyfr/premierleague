package com.example.premierleague.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.premierleague.App
import com.example.premierleague.data.network.ApiMain
import com.example.premierleague.data.network.model.ResponseKlasemen
import com.example.premierleague.data.network.model.ResponseTopskor
import com.example.premierleague.data.network.model.Team
import com.example.premierleague.ui.base.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var apiMain: ApiMain

    private lateinit var subscription: Disposable

    private val _stateKlasemen = MutableLiveData<StateKlasemen.ShowKlasemen>()
    val stateKlasemen: LiveData<StateKlasemen.ShowKlasemen> = _stateKlasemen
    private val _stateTopskor = MutableLiveData<StateTopskor.ShowTopskor>()
    val stateTopskor: LiveData<StateTopskor.ShowTopskor> = _stateTopskor
    private val _stateFav = MutableLiveData<Boolean>()
    val stateFav: LiveData<Boolean> = _stateFav
    private val _stateLoading = MutableLiveData<Int>()
    val stateLoading: LiveData<Int> = _stateLoading

    fun loadKlasemen() {
        subscription = apiMain.getKlasemen()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(
                { _stateKlasemen.value = StateKlasemen.ShowKlasemen(it) },
                { onError() }
            )
    }

    fun loadTopskor() {
        subscription = apiMain.getTopScorer()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(
                { _stateTopskor.value = StateTopskor.ShowTopskor(it) },
                { onError() }
            )
    }

    fun getFav(team_id: Int) {
        subscription = apiMain.getFavTeam(team_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doOnTerminate { onFinish() }
            .subscribe(
                { responseFav ->
                    App.database?.let {
                        it.favDao().insert(responseFav.api.teams[0])
                    }
                    _stateFav.value = true
                },
                {
                    onError()
                    _stateFav.value = false
                    Log.e("FavFail: ", it.message)
                }
            )
    }

    fun loadFav(): Flowable<List<Team>>? {
        return App.database?.let {
            it.favDao().getFavoriteTeam().switchMap { data -> Flowable.just(data) }
        }
    }

    private fun onError() {
        _stateLoading.value = View.GONE
    }

    private fun onFinish() {
        _stateLoading.value = View.GONE
    }

    private fun onStart() {
        _stateLoading.value = View.VISIBLE
    }

    sealed class StateKlasemen {
        data class ShowKlasemen(val data: ResponseKlasemen) : StateKlasemen()
    }

    sealed class StateTopskor {
        data class ShowTopskor(val data: ResponseTopskor) : StateTopskor()
    }

}