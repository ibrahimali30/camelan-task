package com.ibrahim.camelan_task.foursquare.domain.entity

data class PlacesParams(
    var lat: Double? = null,
    var lon: Double? = null,
) {
    fun getFormatedLocation(): String {
        return "$lat,$lon"
    }
}