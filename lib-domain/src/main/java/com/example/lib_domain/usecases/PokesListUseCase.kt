package com.example.lib_domain.usecases

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.Poke
import com.example.lib_domain.repository.PokesListRepository
import javax.inject.Inject

class PokesListUseCase @Inject constructor(
    private val pokesListRepository: PokesListRepository
) {
    suspend operator fun invoke(): ResultType<List<Poke>> =
        pokesListRepository.getPokesList()
}