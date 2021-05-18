package com.ibrahim.camelan_task.foursquare.presentation.di

import com.ibrahim.camelan_task.db.AppDatabase
import com.ibrahim.camelan_task.foursquare.data.repository.PlacesRepositoryImpl
import com.ibrahim.camelan_task.foursquare.data.source.local.PlacesDao
import com.ibrahim.camelan_task.foursquare.data.source.remote.PlacesApiService
import com.ibrahim.camelan_task.foursquare.domain.repsitory.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class PlacesModule {


    @Provides
    fun providesPlacesRepository(placesRepositoryImpl: PlacesRepositoryImpl): PlacesRepository {
        return placesRepositoryImpl
    }

    @Provides
    fun providesPlacesApiService(builder: Retrofit.Builder): PlacesApiService {
        return builder.build().create(PlacesApiService::class.java)
    }

    @Provides
    fun providesPlacesDao(WeatherDatabase: AppDatabase): PlacesDao {
        return WeatherDatabase.placesDao()
    }


}