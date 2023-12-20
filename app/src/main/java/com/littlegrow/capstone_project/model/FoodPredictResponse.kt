package com.littlegrow.capstone_project.model

import com.google.gson.annotations.SerializedName

data class FoodPredictResponse(

	@field:SerializedName("predictions")
	val predictions: List<PredictionsItem>
)

data class PredictionsItem(

	@field:SerializedName("probability")
	val probability: Double,

	@field:SerializedName("label")
	val label: String
)
