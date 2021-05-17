package com.ibrahim.camelan_task.foursquare.data.model.foursquare

data class Venue(
    val categories: List<Category>,
    val delivery: Delivery,
    val id: String,
    val location: Location,
    val name: String,
    val photos: Photos,
    val venuePage: VenuePage
)