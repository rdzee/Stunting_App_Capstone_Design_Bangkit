package com.littlegrow.capstone_project.data.remote

import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.model.InputResponse
import com.littlegrow.capstone_project.model.PredictData
import com.littlegrow.capstone_project.model.PredictResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url
import java.util.UUID

interface ApiService {
    @POST("predict")
    suspend fun postPredict(
        @Body dataPredict : PredictData
    ): PredictResponse

    @FormUrlEncoded
    @POST
    suspend fun postData(
        @Url fullUrl: String,
        @Field ("user_id")userId: String,
        @Field ("profile_id")profileId: String = UUID.randomUUID().toString(),
        @Field ("nama_anak")namaAnak: String,
        @Field ("jenis_kelamin")jenisKelamin: String,
        @Field ("berat_badan")beratBadan: Double,
        @Field ("tinggi_badan")tinggiBadan: Double,
        @Field ("umur")umur: Long,
        @Field ("lingkar_lengan")lingkarLengan: Double,
        @Field ("lingkar_kepala")lingkarKepala: Double,
        @Field ("riwayat_penyakit")riwayatPenyakit: String?,
        @Field ("jarak_kelahiran")jarakKelahiran: Double,
        @Field ("status_user")status: String
    ): InputResponse

    @GET
    suspend fun getListProfile(
        @Url fullUrl: String,
    ): List<DetailDataResponse>
}