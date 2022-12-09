package com.dreamhouse.models

data class Locacao(
    val titulo: String,
    val telefone: String,
    val categoria: String,
    val cidade: String,
    val bairro: String,
    val logradouro: String,
    val numero: Int,
    val image: String,
    val diaria: Double,
    val descricao: String,
    val cliente: ClientId
)