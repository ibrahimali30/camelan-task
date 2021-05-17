package com.ibrahim.camelan_task.foursquare.presentation.model

import com.ibrahim.camelan_task.foursquare.data.model.foursquare.FourSquareResponse

fun FourSquareResponse.mapToUiModel(): List<PlacesUiModel> {
    if (response.groups.isEmpty()) return ArrayList()
    val list = response.groups[0].items.map {
        val location = it.venue.location
        PlacesUiModel(
            it.venue.name,
            location.country,
            location.state,
            location.city,
            location.neighborhood,
        )
    }
    return list
}
