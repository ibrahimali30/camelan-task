package com.ibrahim.camelan_task.foursquare.domain.repsitory

import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.Single

interface PlacesRepository {

    fun fetchPlaces(params: PlacesParams): Single<PlacesUiModel>
    fun getPlacesFromLocalDB(cityName: String): Single<PlacesUiModel>
    fun insertPlacesIntoLocalDB(forecastUiModel: PlacesUiModel)

}