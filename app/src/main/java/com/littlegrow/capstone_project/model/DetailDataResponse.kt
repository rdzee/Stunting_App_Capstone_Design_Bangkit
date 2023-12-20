package com.littlegrow.capstone_project.model

import com.google.gson.annotations.SerializedName

data class DetailDataResponse(

	@field:SerializedName("nama_anak")
	val namaAnak: String,

	@field:SerializedName("umur")
	val umur: Long,

	@field:SerializedName("jarak_kelahiran")
	val jarakKelahiran: Int,

	@field:SerializedName("berat_badan")
	val beratBadan: Double,

	@field:SerializedName("riwayat_penyakit")
	val riwayatPenyakit: String,

	@field:SerializedName("tinggi_badan")
	val tinggiBadan: Double,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String,

	@field:SerializedName("lingkar_lengan")
	val lingkarLengan: Double,

	@field:SerializedName("lingkar_kepala")
	val lingkarKepala: Double,

	@field:SerializedName("status_user")
	val statusUser: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("profile_id")
	val profileId: String
)
