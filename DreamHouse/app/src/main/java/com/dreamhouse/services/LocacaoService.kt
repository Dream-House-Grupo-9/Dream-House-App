package com.dreamhouse.services

import com.dreamhouse.Anuncio
import com.dreamhouse.models.Locacao
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LocacaoService {

    @POST("/cadastrar/anuncio")
    fun createLocacao(@Body locacao: Locacao): Call<Any>

    @GET("/anuncios")
    fun getAnuncio(): Call<List<Anuncio>>

}