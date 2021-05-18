package com.ibrahim.camelan_task.foursquare.data.repository

import com.ibrahim.camelan_task.foursquare.data.model.photo.PlacePhotos
import io.reactivex.Single
import com.ibrahim.camelan_task.foursquare.data.source.remote.PlacesRemoteDataSource
import com.ibrahim.camelan_task.foursquare.domain.repsitory.PlacesRepository
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import com.ibrahim.camelan_task.foursquare.presentation.model.mapToUiModel
import com.ibrahim.camelan_task.foursquare.data.source.local.PlacesLocalDataSource
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import javax.inject.Inject



class PlacesRepositoryImpl @Inject constructor(
    private val placesRemoteDataSource: PlacesRemoteDataSource,
    private val placesLocalDataSource: PlacesLocalDataSource
) : PlacesRepository {

    override fun fetchPlaces(params: PlacesParams): Single<List<PlacesUiModel>> {
        return placesRemoteDataSource.fetchPlaces(params)
                .map { cityWeatherResponse ->
                    cityWeatherResponse.mapToUiModel().also {
                        insertPlacesIntoLocalDB(it)
                    }
                    //on error get from db
                }.onErrorResumeNext(getPlacesFromLocalDB())
    }

    override fun getPlacesFromLocalDB(): Single<List<PlacesUiModel>> {
        return placesLocalDataSource.getPlacesByCityName()
    }

    override fun insertPlacesIntoLocalDB(placesUiModel: List<PlacesUiModel>) {
        placesLocalDataSource.insertPlacesUiModel(placesUiModel)
    }

    override fun getPlacePhotos(venueId: String): Single<PlacePhotos> {
        return placesRemoteDataSource.getPlacePhotos(venueId)
    }


}
