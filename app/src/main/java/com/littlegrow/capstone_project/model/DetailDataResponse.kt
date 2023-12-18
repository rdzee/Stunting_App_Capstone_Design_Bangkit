package com.littlegrow.capstone_project.model

import com.google.gson.annotations.SerializedName

data class DetailDataResponse(

	@field:SerializedName("nama_anak")
	val namaAnak: String? = null,

	@field:SerializedName("umur")
	val umur: Long? = null,

	@field:SerializedName("jarak_kelahiran")
	val jarakKelahiran: Int? = null,

	@field:SerializedName("berat_badan")
	val beratBadan: Int? = null,

	@field:SerializedName("riwayat_penyakit")
	val riwayatPenyakit: String? = null,

	@field:SerializedName("tinggi_badan")
	val tinggiBadan: Int? = null,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String? = null,
)
