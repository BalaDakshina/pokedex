package com.example.lib_data.model

import com.google.gson.annotations.SerializedName

data class PokeDetailsResponse(
    val height: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val weight: Int
)

data class Sprites(
    val other: Other,
)

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: Type
)

data class Type(
    val name: String
)

data class Other(
    val home: Home
)

data class Home(
    @SerializedName("front_default")
    val frontDefault: String
)