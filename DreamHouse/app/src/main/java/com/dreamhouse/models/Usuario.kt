package com.dreamhouse.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    var idUsuario: Int? = null,
    var email: String,
    var senha: String,
    var autenticado: Boolean = false
): Parcelable




