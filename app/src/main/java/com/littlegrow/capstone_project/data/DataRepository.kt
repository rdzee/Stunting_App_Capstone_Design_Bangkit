package com.littlegrow.capstone_project.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.littlegrow.capstone_project.data.local.database.Information
import com.littlegrow.capstone_project.data.local.database.InformationDao
import com.littlegrow.capstone_project.data.remote.ApiService
import com.littlegrow.capstone_project.model.DeleteUpdateResponse
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.model.FoodPredictData
import com.littlegrow.capstone_project.model.InputBodyData
import com.littlegrow.capstone_project.model.InputResponse
import com.littlegrow.capstone_project.model.PredictData
import com.littlegrow.capstone_project.model.PredictResponse
import com.littlegrow.capstone_project.model.PredictionsItem
import com.littlegrow.capstone_project.model.UpdateBodyData
import com.littlegrow.capstone_project.ui.screen.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataRepository(
    private val apiService: ApiService,
    private val informationDao: InformationDao
) {
    fun getAllInformation(): LiveData<Result<List<Information>>> = liveData {
        try {
            val res = informationDao.getAllInfo()
            val data: LiveData<Result<List<Information>>> = res.map {
                Result.Success(it)
            }
            emitSource(data)
        } catch (e: Exception) {
            Log.d(TAG, "getAllInformation: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postData(inputBody: InputBodyData): Flow<Result<InputResponse>> = flow {
        try {
            val response = apiService.postData(
                userId = inputBody.user_id,
                namaAnak = inputBody.nama_anak,
                jenisKelamin = inputBody.jenis_kelamin,
                beratBadan = inputBody.berat_badan,
                tinggiBadan = inputBody.tinggi_badan,
                umur = inputBody.umur,
                lingkarLengan = inputBody.lingkar_lengan,
                lingkarKepala = inputBody.lingkar_kepala,
                riwayatPenyakit = inputBody.riwayat_penyakit,
                jarakKelahiran = inputBody.jarak_kelahiran,
                status = inputBody.status_user
            )
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "postData: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getListData(userId: String): Flow<Result<List<DetailDataResponse>>> = flow {
        try {
            val response = apiService.getListProfile(userId)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "getListData: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailData(profileId: String): Flow<Result<DetailDataResponse>> = flow {
        try {
            val response = apiService.getProfileDetail(profileId)
            emit(Result.Success(response[0]))
        } catch (e: Exception) {
            Log.d(TAG, "getDetailData: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun updateProfile(profileId: String, updateData: UpdateBodyData): Flow<Result<DeleteUpdateResponse>> = flow {
        try {
            val response = apiService.updateProfile(
                profileId = profileId,
                beratBadan = updateData.berat_badan,
                tinggiBadan = updateData.tinggi_badan,
                riwayatPenyakit = updateData.riwayat_penyakit,
                lingkarKepala = updateData.lingkar_kepala,
                lingkarLengan = updateData.lingkar_lengan
            )
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "updateProfile: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }
    fun deleteProfile(profileId: String): Flow<Result<DeleteUpdateResponse>> = flow {
        try {
            val response = apiService.deleteProfile(profileId)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "deleteProfile: ${e.message}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun predictData(listData: List<String>): Flow<Result<PredictResponse>> = flow {
        emit(Result.Loading)
        try {
            val fullUrl = "https://little-grow.et.r.appspot.com/predict"
            val dataPredict = PredictData(listData.map { it.toDouble() })
            val response = apiService.postPredict(fullUrl, dataPredict)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "predictData: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun predictFood(foodPredictData: FoodPredictData): Flow<Result<List<PredictionsItem>>> = flow {
        emit(Result.Loading)
        try {
            val fullUrl = "https://apimodel-zndgno73va-de.a.run.app/predictfood"
            val response = apiService.postFoodPredict(fullUrl, foodPredictData)
            emit(Result.Success(response.predictions))
        } catch (e: Exception) {
            Log.d(TAG, "predictFood: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        private const val TAG = "InformationRepository"

        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(
            apiService: ApiService,
            informationDao: InformationDao
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(apiService, informationDao)
            }.also { instance = it }
    }
}