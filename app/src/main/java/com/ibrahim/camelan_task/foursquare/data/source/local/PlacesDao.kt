package com.ibrahim.camelan_task.foursquare.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel


@Dao
interface PlacesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlacesUiModel(PlacesUiModel: PlacesUiModel):Long

    @Query("select * from PlacesUiModel limit 1")
    fun getPlacesByCityName(): PlacesUiModel


}