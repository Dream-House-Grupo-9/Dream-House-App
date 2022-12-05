package com.dreamhouse.models

data class Locacao(
    val titulo: String,
    val cidade: String,
    val bairro: String,
    val logradouro: String,
    val descricao: String,
    val image: String,
    val cliente: ClientId
)