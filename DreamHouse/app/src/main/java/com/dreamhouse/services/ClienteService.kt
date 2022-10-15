package com.dreamhouse.services

import com.dreamhouse.models.Cliente
import com.dreamhouse.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ClienteService {

    @POST("/clientes")
    fun getOneCliente(@Body Usuario: Usuario): Call<Cliente>

//    @GET("/clientes")
//    fun getOneCliente(@Path("idCliente")idCurso: Int): Call<Cliente>

}