package com.littlegrow.capstone_project.model

data class InputData(
    val name: String = "",
    val weight: String = "",
    val height: String = "",
    val age: Int = 0,
    val diseaseHistory: String = "",
    val birthDistance: String = "",
    val nameError: Boolean = false,
    val weightError: Boolean = false,
    val heightError: Boolean = false,
    val ageError: Boolean = false,
    val diseaseHistoryError: Boolean = false,
    val birthDistanceError: Boolean = false,
)