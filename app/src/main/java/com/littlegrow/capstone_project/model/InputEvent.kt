package com.littlegrow.capstone_project.model

sealed class InputEvent {
    data class NameChanged(val name: String): InputEvent()
    data class WeightChanged(val weight: String): InputEvent()
    data class HeightChanged(val height: String): InputEvent()
    data class BirthDateChanged(val birthDate: Long): InputEvent()
    data class DiseaseHistoryChanged(val diseaseHistory: String): InputEvent()
    data class BirthDistanceChanged(val birthDistance: String): InputEvent()
    data class LingkarLenganChanged(val lingkarLengan: String): InputEvent()
    data class LingkarKepalaChanged(val lingkarKepala: String): InputEvent()
    data class GenderChanged(val gender: String): InputEvent()
    data object Submit: InputEvent()
}