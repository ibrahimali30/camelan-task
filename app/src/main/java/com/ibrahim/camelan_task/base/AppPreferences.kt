package com.ibrahim.camelan_task.base

import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(val sharedPreferences: SharedPreferences) {
    val KEY_LOCATION_MODE = "mode"
    enum class LocationUpdateMode(mode: String){
        REAL_TIME("real time"),
        SINGLE("single")
    }


    fun setLocationUpdateMode(locationUpdateMode: LocationUpdateMode){
        sharedPreferences.edit()
            .putString(KEY_LOCATION_MODE, locationUpdateMode.name)
            .commit()
    }

    fun getLocationUpdateMode(): LocationUpdateMode{
        val mode = sharedPreferences.getString(KEY_LOCATION_MODE, LocationUpdateMode.REAL_TIME.name)
        return LocationUpdateMode.valueOf(mode ?: LocationUpdateMode.REAL_TIME.name)
    }




}