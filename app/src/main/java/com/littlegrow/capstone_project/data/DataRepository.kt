package com.littlegrow.capstone_project.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.littlegrow.capstone_project.data.local.database.Information
import com.littlegrow.capstone_project.data.local.database.InformationDao
import com.littlegrow.capstone_project.data.remote.ApiService
import com.littlegrow.capstone_project.model.InputBodyData
import com.littlegrow.capstone_project.model.InputResponse
import com.littlegrow.capstone_project.model.PredictData
import com.littlegrow.capstone_project.model.PredictResponse
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
            val fullUrl = "https://api-input-zndgno73va-de.a.run.app/profile"
            val response = apiService.postData(
                fullUrl = fullUrl,
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

    fun predictData(listData: List<String>): Flow<Result<PredictResponse>> = flow {
        emit(Result.Loading)
        try {
            val dataPredict = PredictData(listData.map { it.toDouble() })
            val response = apiService.postPredict(dataPredict)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "predictData: ${e.message.toString()}")
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