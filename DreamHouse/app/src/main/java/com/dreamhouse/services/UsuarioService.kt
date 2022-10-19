package com.dreamhouse.services

import com.dreamhouse.models.LoginResponse
import com.dreamhouse.models.Usuario
import com.dreamhouse.models.UsuarioCastradar
import com.dreamhouse.models.UsuarioLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioService {

    @GET("/usuarios/{id_usuario}")
    fun getUsuario(@Path("id_usuario") id_usuario: Int): Call<Usuario>

    @POST("/clientes/login")
    fun login(@Body body: UsuarioLogin): Call<LoginResponse>

    @POST("/clientes")
    fun cadastrar(@Body novoUsuario: UsuarioCastradar): Call<Void>

    @GET("/usuarios")
    fun getAllUsuarios(): Call<List<Usuario>>
}