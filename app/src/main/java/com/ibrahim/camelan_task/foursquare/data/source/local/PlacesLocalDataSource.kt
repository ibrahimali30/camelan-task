package com.ibrahim.camelan_task.foursquare.data.source.local

import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.Single
import javax.inject.Inject


class PlacesLocalDataSource @Inject constructor(
    private val placesDao: PlacesDao
) {

    fun getPlacesByCityName(): Single<List<PlacesUiModel>> {
        return Single.fromCallable {
            placesDao.getPlacesByCityName()
        }
    }

    fun insertPlacesUiModel(placesUiModel: List<PlacesUiModel>) {
         placesDao.refreshCategories(placesUiModel)
    }

}