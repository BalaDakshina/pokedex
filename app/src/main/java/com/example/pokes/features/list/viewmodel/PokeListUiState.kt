package com.example.pokes.features.list.viewmodel

import com.example.lib_domain.model.Poke

sealed class PokeListUiState {
    data object Loading : PokeListUiState()
    data class Success(val data: List<Poke>) : PokeListUiState()
    data object Error : PokeListUiState()
}