package com.dreamhouse.models

data class Locacao(
    val titulo: String,
    val inicioDisponibilidade: String,
    val finalDisponibilidade: String,
    val cidade: String,
    val bairro: String,
    val logradouro: String,
    val detalhe: idDetalhesAnuncio,
    val descricao: String,
    val image: String,
    val cliente: ClientId
)