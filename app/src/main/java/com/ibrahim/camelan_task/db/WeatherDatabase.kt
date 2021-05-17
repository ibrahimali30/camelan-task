package com.ibrahim.camelan_task.db

import androidx.room.*
import com.ibrahim.camelan_task.foursquare.data.source.local.PlacesDao
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel

@Database(
    entities = [
        PlacesUiModel::class
    ],
    version = 1 , exportSchema = false
)

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): PlacesDao

}

