package com.dreamhouse.services

import com.dreamhouse.Anuncio
import com.dreamhouse.models.Locacao
import com.dreamhouse.models.LocacaoDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LocacaoService {

    @POST("/anuncios")
    fun createLocacao(@Body locacao: Locacao): Call<Void>

    @GET("/anuncio")
    fun getAnuncio(): Call<List<Anuncio>>

}