package com.example.lib_data.model

data class PokesListResponse(
    val results: List<Result>
)

data class Result(
    val name: String
)