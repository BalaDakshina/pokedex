package com.example.lib_domain.model

data class PokeDetail(
    val name: String = "",
    val imageUrl: String = "",
    val height: String = "",
    val weight: String = "",
    val baseStats: List<Stat> = emptyList()
)

data class Stat(
    val name: String = "",
    val amount: String
)
