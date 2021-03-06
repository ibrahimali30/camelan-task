package com.ibrahim.camelan_task.foursquare.data.source.local

import androidx.room.*
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel


@Dao
interface PlacesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlacesUiModel(PlacesUiModel: PlacesUiModel):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlacesUiModel(places: List<PlacesUiModel>)

    @Query("select * from PlacesUiModel")
    fun getPlacesByCityName(): List<PlacesUiModel>

    @Query("delete from PlacesUiModel")
    fun deleteAllPlaces()

    @Transaction
    fun refreshCategories(places: List<PlacesUiModel>) {
        deleteAllPlaces()
        insertPlacesUiModel(places)
    }

}