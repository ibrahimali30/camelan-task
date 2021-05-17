package com.ibrahim.camelan_task.foursquare.data.source.local

import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.Single
import javax.inject.Inject


class PlacesLocalDataSource @Inject constructor(
    private val forecastDao: PlacesDao
) {

    fun getPlacesByCityName(cityName: String): Single<List<PlacesUiModel>> {
        return Single.fromCallable {
            forecastDao.getPlacesByCityName()
        }
    }

    fun insertPlacesUiModel(forecastUiModel: List<PlacesUiModel>) {
         forecastDao.insertPlacesUiModel(forecastUiModel)
    }

}