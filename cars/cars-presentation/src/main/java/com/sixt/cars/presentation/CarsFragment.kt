package com.sixt.cars.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import com.sixt.cars.presentation.databinding.FragmentCarsBinding
import com.sixt.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsFragment : BaseFragment<FragmentCarsBinding, CarsViewModel>() {

    override val viewBinder: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCarsBinding =
        FragmentCarsBinding::inflate

    override fun onBindFinished(savedInstanceState: Bundle?) {
        getMap()
    }

    private fun getMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        lifecycle.coroutineScope.launchWhenStarted {
            mapFragment?.awaitMap()?.let { onMapReady(it) }
        }
    }

    private fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }

    override val viewModel: CarsViewModel by viewModels()

    companion object {

        fun instance() = CarsFragment()
    }
}