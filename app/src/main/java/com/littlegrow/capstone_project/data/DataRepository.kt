package com.littlegrow.capstone_project.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.littlegrow.capstone_project.data.local.database.Information
import com.littlegrow.capstone_project.data.local.database.InformationDao
import com.littlegrow.capstone_project.ui.screen.Result

class DataRepository(
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

    companion object {
        private const val TAG = "InformationRepository"

        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(
            informationDao: InformationDao
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(informationDao)
            }.also { instance = it }
    }
}