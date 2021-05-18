package com.ibrahim.camelan_task.foursquare.data.model.photo

data class Photos(
    val count: Int,
    val dupesRemoved: Int,
    val items: List<Item>
)