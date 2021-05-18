package com.ibrahim.camelan_task.foursquare.presentation.model

import android.annotation.SuppressLint
import androidx.room.*
import com.ibrahim.camelan_task.foursquare.data.model.photo.PlacePhotos
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

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
    var subject: BehaviorSubject<String> = BehaviorSubject.create<String>()




}

