package com.ibrahim.camelan_task.foursquare.domain.interactor

import com.ibrahim.camelan_task.foursquare.data.model.photo.PlacePhotos
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.domain.repsitory.PlacesRepository
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.Single
import javax.inject.Inject

class GetPlacePhotosUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    fun getPlacePhotos(venueId: String): Single<PlacePhotos> {
        return placesRepository.getPlacePhotos(venueId)
    }

}