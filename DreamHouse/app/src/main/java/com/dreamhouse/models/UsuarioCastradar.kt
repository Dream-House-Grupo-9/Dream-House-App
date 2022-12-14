package com.dreamhouse.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsuarioCastradar(
    var nome: String,
    var email: String,
    var senha: String
): Parcelable
