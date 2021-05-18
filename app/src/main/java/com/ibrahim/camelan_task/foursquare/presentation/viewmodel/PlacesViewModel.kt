package com.ibrahim.camelan_task.foursquare.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.domain.interactor.GetPlacePhotosUseCase
import com.ibrahim.camelan_task.foursquare.domain.interactor.GetPlacesUseCase
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PlacesViewModel @Inject constructor(
        private val placesUseCase: GetPlacesUseCase,
        private val getPlacePhotos: GetPlacePhotosUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val screenState by lazy { MutableLiveData<PlacesScreenState>() }

    fun getPlaces(params: PlacesParams) {
        screenState.value = PlacesScreenState.Loading

        placesUseCase.fetchPlaces(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {list->
                list.forEach {place ->
                    getPlacePhotos.getPlacePhotos(place.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it.response.photos.items.isEmpty()) return@subscribe
                            val placePhotoUrl = it.response.photos.items[0].getPhotoUrl()
                            place.subject.onNext(placePhotoUrl)
                        },{})
                }
                list
            }
            .subscribe({
                handleSuccess(it)
            }, {
                handleErrorLoadingFromRemote(it)
            }).addTo(compositeDisposable)
    }

    fun getCashedPlaces() {
        screenState.value = PlacesScreenState.Loading

        placesUseCase.getPlacesFromLocalDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccess(it)
            }, {
                handleErrorLoadingFromLocal(it)
            }).addTo(compositeDisposable)
    }

    private fun handleErrorLoadingFromLocal(it: Throwable) {
        screenState.value =PlacesScreenState.ErrorLoadingFromLocal(it)
    }

    private fun handleErrorLoadingFromRemote(it: Throwable) {
        getCashedPlaces()
        screenState.value =PlacesScreenState.ErrorLoadingFromApi(it)
    }

    private fun handleSuccess(it: List<PlacesUiModel>) {
        screenState.value = PlacesScreenState.SuccessAPIResponse(it)
    }

    sealed class PlacesScreenState {
        object Loading : PlacesScreenState()
        class ErrorLoadingFromApi(val error: Throwable) : PlacesScreenState()
        class ErrorLoadingFromLocal(val error: Throwable) : PlacesScreenState()
        class SuccessAPIResponse(val data: List<PlacesUiModel>) : PlacesScreenState()

    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}