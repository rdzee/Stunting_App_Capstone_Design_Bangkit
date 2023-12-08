package com.littlegrow.capstone_project.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "information")
data class Information(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val articleImage: String,

    val articleTitle: String,

    val articleLink: String,

    val articleSource: String
)
