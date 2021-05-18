package com.ibrahim.camelan_task

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.camelan_task.base.AppPreferences
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.presentation.view.adapter.PlacesAdapter
import com.ibrahim.camelan_task.foursquare.presentation.viewmodel.PlacesViewModel
import com.ibrahim.camelan_task.foursquare.presentation.viewmodel.UserLocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error_view.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var placesViewModel : PlacesViewModel

    @Inject
    lateinit var locationManager : UserLocationViewModel

    lateinit var adapter: PlacesAdapter

    @Inject
    lateinit var appPreferences: AppPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeScreenState()
        initRecyclerView()

        locationManager.init(this, ::onLocationGranted, ::onPermissionDenied)
        initObservers()
        initViews()
    }

    private fun initViews() {
        tvUpdateMode.setOnClickListener {
            locationManager.switchLocationUpdateMode()
        }
    }

    private fun initObservers() {
        locationManager.locationUpdateModeLiveData.observe(this, Observer {
            tvUpdateMode.text =
                    if (it == AppPreferences.LocationUpdateMode.SINGLE)
                        "Single"
                    else
                        "RealTime"


        })
    }

    private fun onPermissionDenied() {
        Log.d("TAG", "onPermissionDenied: ")
    }

    private fun onLocationGranted(location: Location) {
        Log.d("TAG", "onLocationGranted: ")
        placesViewModel.getPlaces(PlacesParams(location.latitude, location.longitude))
    }


    private fun initRecyclerView() {
        adapter = PlacesAdapter()
        rvPlaces.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvPlaces.adapter = adapter
    }


    private fun observeScreenState() {
        placesViewModel.screenState.observe(this , Observer {
            onScreenStateChanged(it)
        })
    }

    private fun onScreenStateChanged(state: PlacesViewModel.PlacesScreenState?) {
        when (state) {
            is PlacesViewModel.PlacesScreenState.SuccessAPIResponse -> handleSuccess(state)
            is PlacesViewModel.PlacesScreenState.ErrorLoadingFromApi -> handleErrorLoadingFromApi(state.error)
            else -> {}
        }

        handleLoadingVisibility(state == PlacesViewModel.PlacesScreenState.Loading)
        handleErrorViewsVisibility(state)

    }

    private fun handleErrorViewsVisibility(state: PlacesViewModel.PlacesScreenState?) {
        if (state is PlacesViewModel.PlacesScreenState.ErrorLoadingFromLocal)
            errorViewLayout.visibility = View.VISIBLE
        else
            errorViewLayout.visibility = View.GONE

    }

    private fun handleErrorLoadingFromApi(error: Throwable) {
        btRetry.setOnClickListener {
//            viewModel.getPlaces(sharedViewModel.params)
        }
    }

    private fun handleSuccess(successState: PlacesViewModel.PlacesScreenState.SuccessAPIResponse) {
        adapter.setList(successState.data)
        adapter.notifyDataSetChanged()
    }

    private fun handleLoadingVisibility(show: Boolean) {
        progressBar.visibility = if (show && adapter.data.isEmpty()) View.VISIBLE else View.GONE
    }


}