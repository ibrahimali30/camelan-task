package com.ibrahim.camelan_task.foursquare.presentation.view.activity

import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ibrahim.camelan_task.R
import com.ibrahim.camelan_task.foursquare.presentation.view.adapter.PlacesAdapter
import com.ibrahim.camelan_task.foursquare.presentation.view.helper.UserLocationManager
import com.ibrahim.camelan_task.foursquare.presentation.viewmodel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



const val TAG = "TAGggg"


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var placesViewModel : PlacesViewModel



    private val locationManager = UserLocationManager(this, ::onLocationGranted, ::onPermissionDenied)

    private fun onPermissionDenied() {
        TODO("Not yet implemented")
    }

    private fun onLocationGranted(location: @ParameterName(name = "location") Location) {
        TODO("Not yet implemented")
    }

    lateinit var adapter: PlacesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }



}