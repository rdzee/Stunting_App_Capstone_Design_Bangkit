package com.littlegrow.capstone_project.data.remote

import com.littlegrow.capstone_project.model.DeleteUpdateResponse
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.model.FoodPredictData
import com.littlegrow.capstone_project.model.FoodPredictResponse
import com.littlegrow.capstone_project.model.InputResponse
import com.littlegrow.capstone_project.model.PredictData
import com.littlegrow.capstone_project.model.PredictResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url
import java.util.UUID

interface ApiService {
    @POST
    suspend fun postPredict(
        @Url fullUrl: String,
        @Body dataPredict : PredictData
    ): PredictResponse

    @POST
    suspend fun postFoodPredict(
        @Url fullUrl: String,
        @Body foodPredict: FoodPredictData
    ): FoodPredictResponse

    @FormUrlEncoded
    @POST("profile")
    suspend fun postData(
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

    @GET("profiles/{userId}")
    suspend fun getListProfile(
        @Path ("userId") userId: String
    ): List<DetailDataResponse>

    @GET("detail/{profileId}")
    suspend fun getProfileDetail(
        @Path ("profileId") profileId: String
    ): List<DetailDataResponse>

    @DELETE("delete/{profileId}")
    suspend fun deleteProfile(
        @Path ("profileId") profileId: String
    ): DeleteUpdateResponse

    @FormUrlEncoded
    @PUT("update/{profileId}")
    suspend fun updateProfile(
        @Path ("profileId") profileId: String,
        @Field ("berat_badan")beratBadan: Double,
        @Field ("tinggi_badan")tinggiBadan: Double,
        @Field ("lingkar_lengan")lingkarLengan: Double,
        @Field ("lingkar_kepala")lingkarKepala: Double,
        @Field ("riwayat_penyakit")riwayatPenyakit: String?,
    ): DeleteUpdateResponse
}