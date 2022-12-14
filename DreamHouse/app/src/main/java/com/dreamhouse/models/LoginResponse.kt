package com.dreamhouse.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val idCliente: Int
) : Parcelable
