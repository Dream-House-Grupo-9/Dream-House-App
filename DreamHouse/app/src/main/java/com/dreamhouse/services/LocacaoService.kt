package com.dreamhouse.services

import com.dreamhouse.models.Locacao
import com.dreamhouse.models.LocacaoListCard
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LocacaoService {

    @POST("/anuncios")
    fun createLocacao(@Body locacao: Locacao): Call<Any>

    @GET("/anuncios")
    fun getLocacao(): Call<List<LocacaoListCard>>

    @GET("/anuncios/get-four-house")
    fun getFourLocations(): Call<List<LocacaoListCard>>

    @GET("/anuncios/{id}")
    fun getDetailsLocations(@Path("id") idImovel: String): Call<LocacaoListCard>

}