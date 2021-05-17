package com.ibrahim.camelan_task.foursquare.presentation.di

import android.app.Application
import androidx.room.Room
import com.ibrahim.camelan_task.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideLocalDatabase(context: Application): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, "WeatherDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }


}