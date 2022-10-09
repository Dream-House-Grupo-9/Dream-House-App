package com.dreamhouse.services

import com.dreamhouse.models.Cliente
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ClienteService {

    @GET("/clientes/{idCliente}")
    fun getOneCliente(@Path("idCliente")idCurso: Int): Call<Cliente>

//    @GET("/clientes")
//    fun getOneCliente(@Path("idCliente")idCurso: Int): Call<Cliente>

}