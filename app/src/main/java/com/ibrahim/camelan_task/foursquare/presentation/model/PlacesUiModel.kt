package com.ibrahim.camelan_task.foursquare.presentation.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
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
    fun buildFullAdress(): String {
        return "$country $state $city $neighborhood"
    }

    @Ignore
    var subject: BehaviorSubject<String> = BehaviorSubject.create<String>()




}

