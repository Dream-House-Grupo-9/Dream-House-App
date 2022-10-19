package com.dreamhouse.models

data class Usuario(
    var idUsuario: Int? = null,
    var email: String,
    var senha: String,
    var autenticado: Boolean = false
)




