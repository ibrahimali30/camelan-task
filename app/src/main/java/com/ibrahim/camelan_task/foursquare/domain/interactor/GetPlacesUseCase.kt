package com.ibrahim.camelan_task.foursquare.domain.interactor

import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.domain.repsitory.PlacesRepository
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import io.reactivex.Single
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val forecastRepository: PlacesRepository) {

    fun fetchPlaces(params: PlacesParams): Single<PlacesUiModel> {
        return forecastRepository.fetchPlaces(params)
    }

    fun getPlacesFromLocalDB(cityName: String): Single<PlacesUiModel> {
        return forecastRepository.getPlacesFromLocalDB(cityName)
    }

}