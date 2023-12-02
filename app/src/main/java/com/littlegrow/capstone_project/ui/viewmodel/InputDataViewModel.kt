package com.littlegrow.capstone_project.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littlegrow.capstone_project.model.InputEvent
import com.littlegrow.capstone_project.model.InputData
import com.littlegrow.capstone_project.model.ValidationEvent
import com.littlegrow.capstone_project.model.Validator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class InputDataViewModel : ViewModel() {
    private var _uiState = mutableStateOf(InputData())
    val uiState: State<InputData> = _uiState

    val validationEvent = MutableSharedFlow<ValidationEvent>()

    fun onEvent(event: InputEvent) {
        when (event) {
            is InputEvent.NameChanged -> {
                _uiState.value = _uiState.value.copy(
                    name = event.name
                )
            }

            is InputEvent.WeightChanged -> {
                _uiState.value = _uiState.value.copy(
                    weight = event.weight
                )
            }

            is InputEvent.HeightChanged -> {
                _uiState.value = _uiState.value.copy(
                    height = event.height
                )
            }

            is InputEvent.AgeChanged -> {
                _uiState.value = _uiState.value.copy(
                    age = event.age
                )
            }

            is InputEvent.DiseaseHistoryChanged -> {
                _uiState.value = _uiState.value.copy(
                    diseaseHistory = event.diseaseHistory
                )
            }

            is InputEvent.BirthDistanceChanged -> {
                _uiState.value = _uiState.value.copy(
                    birthDistance = event.birthDistance
                )
            }

            is InputEvent.Submit -> {
                validateInputs()
            }
        }
    }

    private fun validateInputs() {
        val nameResult = Validator.validateName(_uiState.value.name)
        val weightResult = Validator.validateWeight(_uiState.value.weight)
        val heightResult = Validator.validateHeight(_uiState.value.height)
        val ageResult = Validator.validateAge(_uiState.value.age)
        val diseaseHistoryResult = Validator.validateDiseaseHistory(_uiState.value.diseaseHistory)
        val birthDistanceResult = Validator.validateBirthDistance(_uiState.value.birthDistance)

        _uiState.value = _uiState.value.copy(
            nameError = !nameResult.status,
            weightError = !weightResult.status,
            heightError = !heightResult.status,
            ageError = !ageResult.status,
            diseaseHistoryError = !diseaseHistoryResult.status,
            birthDistanceError = !birthDistanceResult.status,
        )

        val hasError = listOf(
            nameResult,
            weightResult,
            heightResult,
            ageResult,
            diseaseHistoryResult,
            birthDistanceResult
        ).any { !it.status }

        viewModelScope.launch {
            if (!hasError) {
                validationEvent.emit(ValidationEvent.Success)
            }
        }
    }
}