package com.dreamhouse.services

import com.dreamhouse.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioService {

    @GET("/usuarios/{id_usuario}")
    fun getUsuario(@Path("id_usuario") id_usuario: Int): Call<Usuario>

    @GET("/usuarios/login/{email}/{senha}")
    fun login(@Path("email") email: String,
              @Path("senha") senha: String): Call<Usuario>

    @POST("/usuarios")
    fun cadastrar(@Body novoUsuario: Usuario): Call<Void>

    @GET("/usuarios")
    fun getAllUsuarios(): Call<List<Usuario>>
}