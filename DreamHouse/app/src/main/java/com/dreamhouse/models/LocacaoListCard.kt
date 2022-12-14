package com.dreamhouse.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocacaoListCard(
    val id: Int,
    val titulo: String,
    val telefoneLocatario: String,
    val cidade: String,
    val bairro: String,
    val logradouro: String,
    val numero: Int,
    val valDiario: Double,
    val image: String,
    val descricao: String,
    val cliente: ClientId
) : Parcelable