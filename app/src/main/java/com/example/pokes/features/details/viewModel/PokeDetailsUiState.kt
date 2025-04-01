package com.example.pokes.features.details.viewModel

import com.example.lib_domain.model.PokeDetail

sealed class PokeDetailsUiState {
    data object Loading : PokeDetailsUiState()
    data class Success(val data: PokeDetail) : PokeDetailsUiState()
    data object Error : PokeDetailsUiState()
}

sealed class DetailsScreenEvent {
    data object OnInitialLoad : DetailsScreenEvent()
}