package com.example.lib_data.repository

import com.example.lib_data.mapper.PokeListMapper
import com.example.lib_data.services.PokesService
import com.example.lib_data.util.CoroutineDispatchersProvider
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.Poke
import com.example.lib_domain.repository.PokesListRepository
import com.example.lib_domain.safeApiCall
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokesListRepositoryImpl @Inject constructor(
    private val pokesService: PokesService,
    private val pokeListMapper: PokeListMapper,
    private val dispatcher: CoroutineDispatchersProvider
) : PokesListRepository {

    override suspend fun getPokesList(): ResultType<List<Poke>> {
        return withContext(dispatcher.default) {
            val response = safeApiCall {
                pokesService.getPokesList()
            }
            pokeListMapper.map(response)
        }
    }
}