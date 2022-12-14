package com.dreamhouse.rest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    private const val baseUrl = "http://192.168.18.222:8080"
//    private const val baseUrl = "http://23.21.133.124:8002"

    private const val BASE_URL_IMGUR = "https://api.imgur.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getRetrofitInstanceImgur(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        return Retrofit.Builder()
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_IMGUR)
            .build()
    }

    fun <T> createServiceImgur(service: Class<T>) : T {
        return getRetrofitInstanceImgur().create(service)
    }
}

