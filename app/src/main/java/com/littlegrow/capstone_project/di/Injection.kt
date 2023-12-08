package com.littlegrow.capstone_project.di

import android.content.Context
import com.littlegrow.capstone_project.data.DataRepository
import com.littlegrow.capstone_project.data.local.database.InformationDatabase

object Injection {
    fun provideRepo(context: Context): DataRepository {
        val database = InformationDatabase.getInstance(context)
        val dao = database.informationDao()
        return DataRepository.getInstance(dao)
    }
}