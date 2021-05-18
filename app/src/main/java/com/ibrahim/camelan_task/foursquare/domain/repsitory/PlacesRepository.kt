package com.ibrahim.camelan_task.foursquare.domain.repsitory

import com.ibrahim.camelan_task.foursquare.data.model.photo.PlacePhotos
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.Single

interface PlacesRepository {

    fun fetchPlaces(params: PlacesParams): Single<List<PlacesUiModel>>
    fun getPlacesFromLocalDB(): Single<List<PlacesUiModel>>
    fun insertPlacesIntoLocalDB(placesUiModel: List<PlacesUiModel>)
    fun getPlacePhotos(venueId: String): Single<PlacePhotos>

}