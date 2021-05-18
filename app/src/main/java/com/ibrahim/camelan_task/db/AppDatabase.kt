package com.ibrahim.camelan_task.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahim.camelan_task.foursquare.data.source.local.PlacesDao
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel

@Database(
    entities = [
        PlacesUiModel::class
    ],
    version = 4 , exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun placesDao(): PlacesDao

}

