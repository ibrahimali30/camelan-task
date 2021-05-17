package com.ibrahim.camelan_task.foursquare.data.source.remote

import io.reactivex.Single
import com.ibrahim.camelan_task.foursquare.data.model.foursquare.FourSquareResponse
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import javax.inject.Inject

class PlacesRemoteDataSource @Inject constructor(
    private val forecastApiService: PlacesApiService
) {

     fun fetchPlaces(params: PlacesParams): Single<FourSquareResponse> {
       return forecastApiService.getPlaces(

       )
     }

}