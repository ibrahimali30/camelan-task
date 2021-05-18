package com.ibrahim.camelan_task.foursquare.presentation.view.helper

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*


class UserLocationManager(
        val activity: AppCompatActivity,
        val onLocationGranted: (location: Location) -> Unit,
        val onPermissionDenied: () -> Unit,
) {

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
        fusedLocationClient.lastLocation
                .addOnSuccessListener(activity) { location ->
                    if (location != null) {
                        onLocationGranted(location)
                    }else{
                        Log.d("TAG", "getLasKnownLocation: null")
                    }
                }


        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(0)
            .setSmallestDisplacement(500f)

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )


    }

}