package com.dreamhouse.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsuarioLogin(
    val email: String,
    val senha: String
): Parcelable
