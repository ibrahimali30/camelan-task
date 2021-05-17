package com.ibrahim.camelan_task.foursquare.presentation.model

import androidx.room.*

@Entity
data class PlacesUiModel (
    var name: String = "",
    var country: String = "",
    var state: String = "",
    var city: String = "",
    var neighborhood: String = "",

    @PrimaryKey
    var id: Int = 0,
    )

