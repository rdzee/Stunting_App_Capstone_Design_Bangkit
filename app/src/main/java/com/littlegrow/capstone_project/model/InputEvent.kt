package com.littlegrow.capstone_project.model

sealed class InputEvent {
    data class NameChanged(val name: String): InputEvent()
    data class WeightChanged(val weight: String): InputEvent()
    data class HeightChanged(val height: String): InputEvent()
    data class AgeChanged(val age: Int): InputEvent()
    data class DiseaseHistoryChanged(val diseaseHistory: String): InputEvent()
    data class BirthDistanceChanged(val birthDistance: String): InputEvent()
    object Submit: InputEvent()
}