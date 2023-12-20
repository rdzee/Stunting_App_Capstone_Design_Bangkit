package com.littlegrow.capstone_project.ui.screen.input

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.littlegrow.capstone_project.data.DataRepository
import com.littlegrow.capstone_project.model.InputBodyData
import com.littlegrow.capstone_project.model.InputData
import com.littlegrow.capstone_project.model.InputEvent
import com.littlegrow.capstone_project.model.ValidationEvent
import com.littlegrow.capstone_project.model.Validator
import com.littlegrow.capstone_project.ui.screen.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class InputDataViewModel(
    private val repository: DataRepository
) : ViewModel() {
    private var _uiState = mutableStateOf(InputData())
    val uiState: State<InputData> = _uiState

    val validationEvent = MutableSharedFlow<ValidationEvent>()

    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
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

            is InputEvent.BirthDateChanged -> {
                _uiState.value = _uiState.value.copy(
                    birthDate = event.birthDate
                )
                _formattedDate.value = dateFormat.format(event.birthDate)
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

            is InputEvent.LingkarKepalaChanged -> {
                _uiState.value = _uiState.value.copy(
                    lingkarKepala = event.lingkarKepala
                )
            }

            is InputEvent.LingkarLenganChanged -> {
                _uiState.value = _uiState.value.copy(
                    lingkarLengan = event.lingkarLengan
                )
            }

            is InputEvent.GenderChanged -> {
                _uiState.value = _uiState.value.copy(
                    gender = event.gender
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
        val birthDateResult = Validator.validateBirthDate(_uiState.value.birthDate)
        val diseaseHistoryResult = Validator.validateDiseaseHistory(_uiState.value.diseaseHistory)
        val birthDistanceResult = Validator.validateBirthDistance(_uiState.value.birthDistance)
        val lingkarLenganResult = Validator.validateLingkarLengan(_uiState.value.lingkarLengan)
        val lingkarKepalaResult = Validator.validateLingkarKepala(_uiState.value.lingkarKepala)
        val genderResult = Validator.validateGender(_uiState.value.gender)

        _uiState.value = _uiState.value.copy(
            nameError = !nameResult.status,
            weightError = !weightResult.status,
            heightError = !heightResult.status,
            birthDateError = !birthDateResult.status,
            diseaseHistoryError = !diseaseHistoryResult.status,
            birthDistanceError = !birthDistanceResult.status,
            lingkarLenganError = !lingkarLenganResult.status,
            lingkarKepalaError = !lingkarKepalaResult.status,
            genderError = !genderResult.status
        )

        val hasError = listOf(
            nameResult,
            weightResult,
            heightResult,
            birthDateResult,
            diseaseHistoryResult,
            birthDistanceResult,
            lingkarLenganResult,
            lingkarKepalaResult
        ).any { !it.status }

        viewModelScope.launch {
            if (!hasError) {
                val predictData: List<String> = listOf(
                    _uiState.value.height,
                    _uiState.value.weight,
                    _uiState.value.lingkarKepala,
                    _uiState.value.lingkarLengan
                )
                getPrediction(predictData)
            }
        }
    }

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val _formattedDate = mutableStateOf("")
    val formattedDate
        get() = _formattedDate

    private val _prediction = MutableStateFlow("")
    val prediction: StateFlow<String>
        get() = _prediction


    private fun getPrediction(listData: List<String>?) {
        if (listData != null) {
            viewModelScope.launch {
                repository.predictData(listData)
                    .collect { predictionResult ->
                        when (predictionResult) {
                            is Result.Success -> {
                                _prediction.value = predictionResult.data.prediction.uppercase()
                                postData()
                            }

                            is Result.Error -> {
                                Log.w("InputDataViewModel", predictionResult.error)
                                validationEvent.emit(ValidationEvent.Error)
                            }

                            Result.Loading -> {
                                validationEvent.emit(ValidationEvent.Loading)
                            }
                        }
                    }
            }
        }
    }

    private fun postData() {
        viewModelScope.launch {
            if (currentUserId != null) {
                val inputBodyData = InputBodyData(
                    user_id = currentUserId,
                    nama_anak = _uiState.value.name,
                    jenis_kelamin = _uiState.value.gender,
                    berat_badan = _uiState.value.weight.toDouble(),
                    tinggi_badan = _uiState.value.height.toDouble(),
                    umur = getAge(_uiState.value.birthDate),
                    lingkar_lengan = _uiState.value.lingkarLengan.toDouble(),
                    lingkar_kepala = _uiState.value.lingkarKepala.toDouble(),
                    riwayat_penyakit = _uiState.value.diseaseHistory,
                    jarak_kelahiran = _uiState.value.birthDistance.toDouble(),
                    status_user = prediction.value
                )
                repository.postData(inputBodyData).collect{inputResult ->
                    when(inputResult) {
                        is Result.Success -> {
                            validationEvent.emit(ValidationEvent.Success)
                        }
                        is Result.Error -> {
                            Log.w("InputDataViewModel", inputResult.error)
                            validationEvent.emit(ValidationEvent.Error)
                        }
                        is Result.Loading -> {
                            validationEvent.emit(ValidationEvent.Loading)
                        }
                    }
                }
            }
        }
    }

    private fun getAge(birthDate: Long): Long {
        return System.currentTimeMillis() - birthDate
    }
}
