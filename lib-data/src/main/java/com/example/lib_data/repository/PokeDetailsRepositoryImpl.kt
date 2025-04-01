package com.example.lib_data.repository

import com.example.lib_data.mapper.PokeDetailsMapper
import com.example.lib_data.model.PokeDetailsResponse
import com.example.lib_data.services.PokesService
import com.example.lib_data.util.CoroutineDispatchersProvider
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.PokeDetail
import com.example.lib_domain.repository.PokeDetailsRepository
import com.example.lib_domain.safeApiCall
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokeDetailsRepositoryImpl @Inject constructor(
    private val pokesService: PokesService,
    private val pokeDetailsMapper: PokeDetailsMapper,
    private val dispatcher: CoroutineDispatchersProvider
) : PokeDetailsRepository {

    override suspend fun getPokeDetails(id: String): ResultType<PokeDetail> {
        return withContext(dispatcher.default) {
            val response: ResultType<PokeDetailsResponse> = safeApiCall {
                pokesService.getPokeDetails(id)
            }
            pokeDetailsMapper.map(response)
        }
    }
}