package com.sixt.cars.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sixt.cars.presentation.databinding.FragmentCarsBinding
import com.sixt.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsFragment : BaseFragment<FragmentCarsBinding, CarsViewModel>() {

    override val viewBinder: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCarsBinding =
        FragmentCarsBinding::inflate

    override fun onBindFinished(savedInstanceState: Bundle?) {
        //left to be used later if needed
    }

    override val viewModel: CarsViewModel by viewModels()

    companion object {

        fun instance() = CarsFragment()
    }
}