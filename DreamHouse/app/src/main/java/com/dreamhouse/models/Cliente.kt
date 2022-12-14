package com.dreamhouse.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Cliente(
    var idCliente: Int,
    var cpf: String,
    var email: String,
    var telefone: Int,
    var celular: Int,
    var fkUsuario: Usuario
) : Parcelable

