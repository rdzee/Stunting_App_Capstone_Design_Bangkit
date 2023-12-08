package com.littlegrow.capstone_project.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InformationDao {
    @Insert
    fun insertAll(vararg information: Information)

    @Query("SELECT * FROM information")
    fun getAllInfo (): LiveData<List<Information>>
}