package com.dreamhouse.models

data class Cliente(
    var idCliente: Int,
    var cpf: String,
    var email: String,
    var telefone: Int,
    var celular: Int,
    var fkUsuario: Usuario
)

