package com.ibrahim.camelan_task.foursquare.presentation.view.activity


import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ibrahim.camelan_task.R
import com.ibrahim.camelan_task.base.AppPreferences
import com.ibrahim.camelan_task.foursquare.domain.entity.PlacesParams
import com.ibrahim.camelan_task.foursquare.presentation.model.PlacesUiModel
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
    @Inject
    lateinit var appPreferences: AppPreferences
    lateinit var adapter: PlacesAdapter

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
            tvUpdateMode.text = // TODO: 5/18/2021 move to string resources
                    if (it == AppPreferences.LocationUpdateMode.SINGLE)
                        "Single"
                    else
                        "RealTime"
        })
    }

    private fun onPermissionDenied() {
        Toast.makeText(this,"Location Permission denied",Toast.LENGTH_LONG).show()
    }

    private fun onLocationGranted(location: Location) {
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
        handleErrorViewsVisibility(state)
        handleLoadingVisibility(state == PlacesViewModel.PlacesScreenState.Loading)

        when (state) {
            is PlacesViewModel.PlacesScreenState.SuccessAPIResponse -> handleSuccess(state.data)
            is PlacesViewModel.PlacesScreenState.ErrorLoadingFromLocal -> handleError(state.error)
            is PlacesViewModel.PlacesScreenState.SuccessLocalResponse ->{
                showCashedDataAlert()
                handleSuccess(state.data)
            }
            else -> {}
        }
    }

    private fun showCashedDataAlert() {
        Snackbar.make(parenView, "check your internet connection", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                locationManager.locationUpdateModeLiveData.value?.let {
                    locationManager.setAppLocationUpdateMode(it)
                }
            }
            .setActionTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    }

    private fun handleErrorViewsVisibility(state: PlacesViewModel.PlacesScreenState?) {
        if (state is PlacesViewModel.PlacesScreenState.ErrorLoadingFromLocal)
            showErrorViews()
        else
            errorViewLayout.visibility = View.GONE

    }

    private fun handleError(error: Throwable) {
        showErrorViews()
        ivErrorIcon.setImageResource(R.drawable.ic_baseline_cloud_off_24)
        tvErrorMsg.text = "Something went wrong!!"
    }

    private fun showErrorViews() {
        adapter.clear()
        errorViewLayout.visibility = View.VISIBLE
    }

    private fun handleSuccess(data: List<PlacesUiModel>) {
        if (data.isEmpty()){
            showEmptyResult()
        }else{
            adapter.setList(data)
        }
    }

    private fun showEmptyResult() {
        showErrorViews()
        ivErrorIcon.setImageResource(R.drawable.ic_baseline_error_outline_24)
        tvErrorMsg.text = "no data found!!"
    }

    private fun handleLoadingVisibility(show: Boolean) {
        progressBar.visibility = if (show && adapter.data.isEmpty()) View.VISIBLE else View.GONE
    }
    
}