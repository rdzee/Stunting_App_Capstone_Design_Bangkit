package com.littlegrow.capstone_project.model

data class InputBodyData(
    val user_id: String,
    val nama_anak: String,
    val jenis_kelamin: String,
    val berat_badan: Double,
    val tinggi_badan: Double,
    val umur: Long,
    val lingkar_lengan: Double,
    val lingkar_kepala: Double,
    val riwayat_penyakit: String?,
    val jarak_kelahiran: Double,
    val status_user: String
)
