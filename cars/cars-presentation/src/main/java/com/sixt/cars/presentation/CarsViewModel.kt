package com.sixt.cars.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sixt.cars.domain.usecases.GetCarsUseCase
import com.sixt.core.base.BaseViewModel
import com.sixt.core.di.CoroutineDispatcherIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase,
    @CoroutineDispatcherIO private val coroutineContext: CoroutineDispatcher
) : BaseViewModel() {

    private val TAG: String =  "CarsViewModel"

    init {
        getCars()
    }

    private fun getCars() {
        viewModelScope.launch(coroutineContext) {
            getCarsUseCase.getCars()
                .onStart {

                }
                .catch {
                    Log.e(TAG, "getCars: error $it")
                }
                .collect {
                    Log.i(TAG, "getCars: success $it")
                }
        }
    }

    fun any() {

    }

}