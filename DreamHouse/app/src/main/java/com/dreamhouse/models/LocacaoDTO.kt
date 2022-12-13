package com.dreamhouse.models

import java.util.*

data class LocacaoDTO(
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
    val dtPublicacao: Date?,
    val inicioDisponibilidade: Date?,
    val finalDisponibilidade: Date?,
    val cliente: ClienteDTO,
    val detalhe: DetalheDTO?
)

data class ClienteDTO(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val autenticado: Boolean
)

data class DetalheDTO(
    val idDetalhesAnuncio: Int,
    val ativoDiaria: Boolean,
    val ativoSemanal: Boolean,
    val ativoMensal: Boolean,
    val valorDiaria: Int,
    val valorSemanal: Int,
    val valorMes: Int,
    val qtdDormitorios: Int,
    val qtdToaletes: Int,
    val garagem: Boolean,
    val areaDeTrabalho: Boolean,
    val mobiliada: Boolean,
    val categoria: String,
    val qtdCurtidas: Int
)
