package com.sixt.cars.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.infoWindowClickEvents
import com.google.maps.android.ktx.markerClickEvents
import com.sixt.cars.domain.entities.Car
import com.sixt.cars.presentation.R
import com.sixt.cars.presentation.bitmapDescriptorFromVector
import com.sixt.cars.presentation.databinding.FragmentCarsBinding
import com.sixt.cars.presentation.main.mvi.CarsIntents
import com.sixt.cars.presentation.main.mvi.CarsViewState
import com.sixt.cars.presentation.setMapStyle
import com.sixt.core.base.BaseFragment
import com.sixt.core.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CarsFragment : BaseFragment<FragmentCarsBinding, CarsViewModel>() {

    override val viewBinder: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCarsBinding =
        FragmentCarsBinding::inflate

    @Inject
    lateinit var carsListAdapter: CarsListAdapter

    private lateinit var googleMap: GoogleMap
    private lateinit var markers: Map<Int, Marker?>

    override fun onBindFinished(savedInstanceState: Bundle?) {
        setupRecycler()

        lifecycleScope.launch {
            renderMap()

            viewModel.viewState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { render(it) }
        }

    }

    private fun setupRecycler() {
        binding.carsRv.adapter = carsListAdapter
        binding.carsRv.withLinearSpaceItemDecoration(R.dimen.margin_small)
        carsListAdapter.onCarClickedCallback = this::onCarClicked
    }

    private fun onCarClicked(position: Int) {
        lifecycleScope.launch {
            viewModel.intents.send(CarsIntents.SelectCar(position))
        }
    }

    private fun render(viewState: CarsViewState) {
        when (viewState) {
            is CarsViewState.Loading -> requireContext().shortToast("loading...")
            is CarsViewState.Error -> {
                val errorMessage = viewState.throwable.networkErrorMessage(requireContext())
                    ?: viewState.throwable.localizedMessage
                requireContext().shortToast(errorMessage)
            }
            is CarsViewState.Cars -> renderCars(viewState)
            is CarsViewState.CarSelection -> renderCarSelection(viewState)
            else -> return
        }
    }

    private fun renderCars(viewState: CarsViewState.Cars) {
        carsListAdapter.submitList(viewState.data)
        showCarsOnMap(googleMap, viewState.data)
    }

    private fun renderCarSelection(viewState: CarsViewState.CarSelection) {
        /**
         * check if markers are available or not, if not show them first
         * this is to handle markers being destroyed after rotating the screen
         */
        if (this::markers.isInitialized.not()) showCarsOnMap(googleMap, viewState.cars)
        carsListAdapter.submitList(viewState.cars)
        binding.carsRv.smoothScrollToPosition(viewState.position)
        moveCameraToCar(viewState.car)
        markers[viewState.oldSelection].updateIcon(isSelected = false)
        markers[viewState.position].updateIcon(isSelected = true)
        markers[viewState.position]?.showInfoWindow()
    }

    private fun showCarsOnMap(googleMap: GoogleMap, data: List<Car>) {
        markers = data.mapIndexed { index, car ->
            val marker = addMarker(googleMap, car)
            marker.updateIcon(car.isSelected)
            marker?.tag = index
            marker
        }.associateBy {
            it?.tag as Int
        }
        moveCameraToCar(data.firstOrNull())
        markers[0]?.showInfoWindow()
        consumeMarkersClicks(googleMap)
    }

    private fun consumeMarkersClicks(googleMap: GoogleMap) {
        lifecycleScope.launch {
            googleMap.markerClickEvents().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    it.showInfoWindow()
                    onMarkerClicked(it)
                }
        }
    }

    private fun onMarkerClicked(marker: Marker?) {
        val position = marker?.tag as? Int
        position?.let {
            lifecycleScope.launch {
                viewModel.intents.send(CarsIntents.SelectCar(position))
            }
        }
    }

    private fun moveCameraToCar(car: Car?) = car?.let {
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(LatLng(car.latitude, car.longitude), 14f)
        )
    }

    private fun addMarker(googleMap: GoogleMap, car: Car) = googleMap.addMarker {
        position(LatLng(car.latitude, car.longitude))
        title(car.name)
        snippet(car.licensePlate)
    }

    private fun Marker?.updateIcon(isSelected: Boolean) = this?.let {
        val icon =
            requireContext().bitmapDescriptorFromVector(
                R.drawable.ic_car_placeholder,
                if (isSelected) R.color.colorPrimary else R.color.black
            )
        setIcon(icon)
    }

    private suspend fun renderMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.awaitMap()?.let {
            it.setMapStyle(requireContext(), R.raw.map_style)
            googleMap = it
            viewModel.intents.send(CarsIntents.InitCars)
        }
    }

    override fun onDestroyView() {
        binding.carsRv.adapter = null
        super.onDestroyView()
    }

    override val viewModel: CarsViewModel by viewModels()

    companion object {

        fun instance() = CarsFragment()
    }
}