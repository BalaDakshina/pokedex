package com.example.lib_domain.repository

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.Poke

interface PokesListRepository {
    suspend fun getPokesList(): ResultType<List<Poke>>
}