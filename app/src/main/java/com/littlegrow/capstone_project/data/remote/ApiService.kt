package com.littlegrow.capstone_project.data.remote

import com.littlegrow.capstone_project.model.PredictData
import com.littlegrow.capstone_project.model.PredictResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("predict")
    suspend fun postPredict(
        @Body dataPredict : PredictData
    ): PredictResponse
}