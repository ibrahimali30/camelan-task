package com.ibrahim.camelan_task.foursquare.presentation.di

import com.ibrahim.camelan_task.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import com.ibrahim.camelan_task.foursquare.data.repository.PlacesRepositoryImpl
import com.ibrahim.camelan_task.foursquare.domain.repsitory.PlacesRepository
import com.ibrahim.camelan_task.foursquare.data.source.remote.PlacesApiService
import com.ibrahim.camelan_task.foursquare.data.source.local.PlacesDao
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class PlacesModule {


    @Provides
    fun providesPlacesRepository(forecastRepositoryImpl: PlacesRepositoryImpl): PlacesRepository = forecastRepositoryImpl

    @Provides
    fun providesPlacesApiService(builder: Retrofit.Builder): PlacesApiService {
        return builder.build().create(PlacesApiService::class.java)
    }


    @Provides
    fun providesPlacesDao(WeatherDatabase: WeatherDatabase): PlacesDao = WeatherDatabase.forecastDao()


}