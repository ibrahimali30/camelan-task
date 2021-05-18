package com.ibrahim.camelan_task.foursquare.data.model.photo

data class Item(
    val checkin: Checkin,
    val createdAt: Int,
    val height: Int,
    val id: String,
    val prefix: String,
    val source: Source,
    val suffix: String,
    val visibility: String,
    val width: Int
){
    fun getPhotoUrl(): String{
        return "${prefix}300x300${suffix}"
    }
}