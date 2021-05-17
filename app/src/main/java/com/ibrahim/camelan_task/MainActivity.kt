package com.ibrahim.camelan_task

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.presentation.view.adapter.PlacesAdapter
import com.ibrahim.camelan_task.foursquare.presentation.view.helper.UserLocationManager
import com.ibrahim.camelan_task.foursquare.presentation.viewmodel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var forecastViewModel : PlacesViewModel



    private val locationManager = UserLocationManager(this, ::onLocationGranted, ::onPermissionDenied)



    lateinit var adapter: PlacesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager.askForPermission()

    }

    private fun onPermissionDenied() {
        Log.d("TAG", "onPermissionDenied: ")
    }

    private fun onLocationGranted(location: Location) {
        Log.d("TAG", "onLocationGranted: ")
    }

}