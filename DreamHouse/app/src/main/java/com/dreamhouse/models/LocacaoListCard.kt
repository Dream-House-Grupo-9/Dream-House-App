package com.dreamhouse.models

data class LocacaoListCard(
    val id: Int,
    val cidade: String,
    val bairro: String,
    val titulo: String,
    val logradouro: String,
    val descricao: String,
    val image: String,
    val cliente: ClientId
)