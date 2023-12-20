package com.littlegrow.capstone_project.model

data class FoodPredictData(
    val height: Double,
    val weight: Double,
    val head_circumference: Double,
    val arm_circumference: Double,
    val history_of_illness: Boolean,
    val birth_spacing: Int
)
