package com.littlegrow.capstone_project.model

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("prediction")
	val prediction: Int
)
