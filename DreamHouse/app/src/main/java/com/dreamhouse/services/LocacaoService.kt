package com.dreamhouse.services

import com.dreamhouse.models.Locacao
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LocacaoService {

    @POST("/anuncios")
    fun createLocacao(@Body locacao: Locacao): Call<Any>

}