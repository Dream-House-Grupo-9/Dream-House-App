package com.dreamhouse.services

import com.dreamhouse.models.ImgurUploadJson
import retrofit2.Call
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImgurApi {

    @Multipart
    @POST("/3/image")
    fun postImage(@Header("Authorization") clientId: String, @Part image: MultipartBody.Part): Call<ImgurUploadJson>
}