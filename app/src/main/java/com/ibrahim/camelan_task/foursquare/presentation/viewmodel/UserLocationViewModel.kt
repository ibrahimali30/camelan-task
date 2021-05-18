package com.ibrahim.camelan_task.foursquare.presentation.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.*
import com.ibrahim.camelan_task.base.AppPreferences
import javax.inject.Inject


class UserLocationViewModel @Inject constructor(
        private val appPreferences: AppPreferences
):ViewModel() {



    lateinit var activity: AppCompatActivity
    lateinit var onLocationGranted: (location: Location) -> Unit
    lateinit var onPermissionDenied: () -> Unit
    var locationUpdateModeLiveData = MutableLiveData<AppPreferences.LocationUpdateMode>()

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(activity)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.lastLocation?.let {
                onLocationGranted(it)
            }
        }
    }


    private val requestPermissionLauncher by lazy {
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getLocation()
            } else {
                onPermissionDenied()
            }
        }
    }


    fun init(activity: AppCompatActivity,
             onLocationGranted: (location: Location) -> Unit,
             onPermissionDenied: () -> Unit
    ) {
        this.activity = activity
        this.onLocationGranted = onLocationGranted
        this.onPermissionDenied = onPermissionDenied

        val mode = this.appPreferences.getLocationUpdateMode()
        setAppLocationUpdateMode(mode)
    }

    private fun askForPermission() {
        val permissionStatus = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)

        if(permissionStatus == PackageManager.PERMISSION_GRANTED ) {
            getLocation()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {

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