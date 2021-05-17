package com.ibrahim.camelan_task.foursquare.data.repository

import io.reactivex.Single
import com.ibrahim.camelan_task.foursquare.data.source.remote.PlacesRemoteDataSource
import com.ibrahim.camelan_task.foursquare.domain.repsitory.PlacesRepository
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
import com.ibrahim.camelan_task.foursquare.presentation.model.mapToUiModel
import com.ibrahim.camelan_task.foursquare.data.source.local.PlacesLocalDataSource
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import javax.inject.Inject



class PlacesRepositoryImpl @Inject constructor(
    private val forecastRemoteDataSource: PlacesRemoteDataSource,
    private val forecastLocalDataSource: PlacesLocalDataSource
) : PlacesRepository {

    override fun fetchPlaces(params: PlacesParams): Single<PlacesUiModel> {
        return forecastRemoteDataSource.fetchPlaces(params)
                .map { cityWeatherResponse ->
                    cityWeatherResponse.mapToUiModel().also {
                        insertPlacesIntoLocalDB(it)
                    }
                    //on error get from db
                }.onErrorResumeNext(getPlacesFromLocalDB(params.cityName?: ""))
    }

    override fun getPlacesFromLocalDB(cityName: String): Single<PlacesUiModel> {
        return forecastLocalDataSource.getPlacesByCityName(cityName)
    }

    override fun insertPlacesIntoLocalDB(forecastUiModel: PlacesUiModel) {
        forecastLocalDataSource.insertPlacesUiModel(forecastUiModel)
    }


}
