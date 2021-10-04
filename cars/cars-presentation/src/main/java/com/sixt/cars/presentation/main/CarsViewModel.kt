package com.sixt.cars.presentation.main

import androidx.lifecycle.viewModelScope
import com.sixt.cars.domain.entities.Car
import com.sixt.cars.domain.usecases.GetCarsFirstSelectedUseCase
import com.sixt.cars.presentation.main.mvi.CarsIntents
import com.sixt.cars.presentation.main.mvi.CarsViewState
import com.sixt.core.base.BaseViewModel
import com.sixt.core.di.CoroutineDispatcherIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsFirstSelectedUseCase,
    @CoroutineDispatcherIO private val coroutineContext: CoroutineDispatcher
) : BaseViewModel() {

    val intents = Channel<CarsIntents>()

    private val _viewState = MutableStateFlow<CarsViewState>(CarsViewState.Idle)
    val viewState: StateFlow<CarsViewState> get() = _viewState

    private lateinit var carsList: List<Car>
    private var curSelectedCarIndex = 0

    init {
        viewModelScope.launch(coroutineContext) {
            intents.consumeAsFlow().collect { processIntents(it) }
        }
    }

    private fun processIntents(intent: CarsIntents) {
        when (intent) {
            is CarsIntents.InitCars -> reduceInitCarsIntent()
            is CarsIntents.SelectCar -> reduceSelectCar(intent.position)
        }
    }

    private fun reduceInitCarsIntent() {
        /**
         * check if cars list was fetched before or not, if not get them
         * this is to avoid re-fetching the cars after rotating the screen
         */
        if (this::carsList.isInitialized) return

        viewModelScope.launch(coroutineContext) {
            getCarsUseCase.getCarsFirstSelected()
                .onStart { _viewState.emit(CarsViewState.Loading) }
                .catch { _viewState.emit(CarsViewState.Error(it)) }
                .collect {
                    carsList = it
                    _viewState.emit(CarsViewState.Cars(it))
                }
        }
    }

    private fun reduceSelectCar(position: Int) {
        if (position == curSelectedCarIndex) return
        viewModelScope.launch(coroutineContext) {
            carsList = carsList.updateCarSelection(position, curSelectedCarIndex)
            _viewState.emit(
                CarsViewState.CarSelection(
                    curSelectedCarIndex,
                    position,
                    carsList[position],
                    carsList
                )
            )
            curSelectedCarIndex = position
        }
    }

    private fun List<Car>.updateCarSelection(newIndex: Int, oldIndex: Int): List<Car> =
        toMutableList().apply {
            this[newIndex] = this[newIndex].copy(isSelected = true)
            this[oldIndex] = this[oldIndex].copy(isSelected = false)
        }

}