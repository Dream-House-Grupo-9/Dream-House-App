package com.dreamhouse.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Locacao(
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

//{   "titulo": "Casa zona leste ",
// "telefoneLocatario": "11976137480",
// "cidade": "Osasco",
// "bairro": "Bairro do Henri",
// "logradouro": "rua do Henri",
// "numero": 1,
// "valDiario": 85.10,
// "image": "imagem1,imagem2,imagem3,imagem4",
// "descricao": "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
// "cliente": {      "id": 1    } }
