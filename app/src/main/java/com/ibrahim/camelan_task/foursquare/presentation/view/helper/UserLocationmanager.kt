package com.ibrahim.camelan_task.foursquare.presentation.view.helper

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.ibrahim.camelan_task.base.AppPreferences


class UserLocationManager(
        val activity: AppCompatActivity,
        val onLocationGranted: (location: Location) -> Unit,
        val onPermissionDenied: () -> Unit,
) {

    lateinit var appPreferences: AppPreferences

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(activity)
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.lastLocation?.let {
                onLocationGranted(it)
            }
        }
    }


    private val requestPermissionLauncher=
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getLasKnownLocation()
            } else {
                onPermissionDenied()
            }
        }

    fun init(appPreferences: AppPreferences) {
        this.appPreferences = appPreferences
        val mode = this.appPreferences.getLocationUpdateMode()
        setAppLocationUpdateMode(mode)
    }

     var locationUpdateModeLiveData = MutableLiveData<AppPreferences.LocationUpdateMode>()




    fun askForPermission() {
        val permissionStatus = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)

        if(permissionStatus == PackageManager.PERMISSION_GRANTED ) {
            getLasKnownLocation()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLasKnownLocation() {

        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(0)
            .setSmallestDisplacement(500f)

        if (locationUpdateModeLiveData.value == AppPreferences.LocationUpdateMode.SINGLE)
            locationRequest.numUpdates = 1

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )


    }


    fun setAppLocationUpdateMode(mode: AppPreferences.LocationUpdateMode) {
        fusedLocationClient.removeLocationUpdates(locationCallback)
        locationUpdateModeLiveData.value = mode
        askForPermission()
    }

    fun switchLocationUpdateMode() {
        val newMode = if (locationUpdateModeLiveData.value == AppPreferences.LocationUpdateMode.SINGLE)
            AppPreferences.LocationUpdateMode.REAL_TIME
        else
            AppPreferences.LocationUpdateMode.SINGLE

        appPreferences.setLocationUpdateMode(newMode)
        setAppLocationUpdateMode(newMode)
    }

}