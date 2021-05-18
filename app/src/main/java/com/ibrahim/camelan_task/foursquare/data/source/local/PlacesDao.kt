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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlacesUiModel(PlacesUiModel: List<PlacesUiModel>)

    @Query("select * from PlacesUiModel")
    fun getPlacesByCityName(): List<PlacesUiModel>


}