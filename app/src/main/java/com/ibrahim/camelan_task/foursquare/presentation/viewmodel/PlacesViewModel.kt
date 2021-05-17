package com.ibrahim.camelan_task.foursquare.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.domain.interactor.GetPlacesUseCase
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PlacesViewModel @Inject constructor(
        private val refreshPlacesUseCase: GetPlacesUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val screenState by lazy { MutableLiveData<PlacesScreenState>() }

    fun getPlaces(params: PlacesParams) {
        screenState.value = PlacesScreenState.Loading

        refreshPlacesUseCase.fetchPlaces(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccess(it)
            }, {
                screenState.value =PlacesScreenState.ErrorLoadingFromApi(it)
            }).addTo(compositeDisposable)
    }

    private fun handleSuccess(it: List<PlacesUiModel>) {

        screenState.value = PlacesScreenState.SuccessAPIResponse(it)
    }

    sealed class PlacesScreenState {
        object Loading : PlacesScreenState()
        class ErrorLoadingFromApi(val error: Throwable) : PlacesScreenState()
        class SuccessAPIResponse(val data: List<PlacesUiModel>) : PlacesScreenState()


    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}