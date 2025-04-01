package com.example.lib_data.mapper

import com.example.lib_data.model.PokesListResponse
import com.example.lib_domain.ResultType
import com.example.lib_domain.asResult
import com.example.lib_domain.model.Poke
import javax.inject.Inject

class PokeListMapper @Inject constructor() {
    fun map(response: ResultType<PokesListResponse>): ResultType<List<Poke>> =
        response.asResult { data ->
            data.results.map {
                Poke(name = it.name)
            }
        }
}