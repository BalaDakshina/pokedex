package com.example.lib_domain.usecases

import com.example.lib_domain.repository.PokeDetailsRepository
import javax.inject.Inject

class PokeDetailsUseCase @Inject constructor(
    private val pokeDetailsRepository: PokeDetailsRepository
) {
    suspend operator fun invoke(pokeName: String) =
        pokeDetailsRepository.getPokeDetails(pokeName)
}