package com.ibrahim.camelan_task.foursquare.presentation.model

import androidx.room.*
import com.ibrahim.camelan_task.foursquare.data.model.photo.PlacePhotos
import io.reactivex.Observable
import io.reactivex.Single

@Entity
data class PlacesUiModel (
    var name: String = "",
    var country: String = "",
    var state: String = "",
    var city: String = "",
    var neighborhood: String = "",
    var prefix: String = "",
    var suffix: String = "",

    @PrimaryKey
    var id: String,
    ){

    @Ignore
    lateinit var observable: Single<PlacePhotos>




}

