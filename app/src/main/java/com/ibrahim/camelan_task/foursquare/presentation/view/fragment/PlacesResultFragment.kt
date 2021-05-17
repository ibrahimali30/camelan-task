package com.ibrahim.camelan_task.foursquare.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.camelan_task.R
import com.ibrahim.camelan_task.foursquare.presentation.view.adapter.PlacesAdapter
import com.ibrahim.camelan_task.foursquare.presentation.viewmodel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forecast_result.*
import kotlinx.android.synthetic.main.layout_error_view.*


@AndroidEntryPoint
class PlacesResultFragment(
        val viewModel: PlacesViewModel
) : Fragment(R.layout.fragment_forecast_result){

    lateinit var adapter: PlacesAdapter


    private val sharedViewModel by lazy {
//        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeScreenState()
        initRecyclerView()

//        viewModel.getPlaces(sharedViewModel.params)
//
//        tvTitle.text = sharedViewModel.cityName
    }


    private fun initRecyclerView() {
        adapter = PlacesAdapter()
        rvPlaces.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvPlaces.adapter = adapter
    }


    private fun observeScreenState() {
        viewModel.screenState.observe(viewLifecycleOwner , Observer {
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
        if (state is PlacesViewModel.PlacesScreenState.ErrorLoadingFromApi)
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
//        adapter.setList(successState.mapToAdapterUiModel())
        adapter.notifyDataSetChanged()
    }

    private fun handleLoadingVisibility(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }




}