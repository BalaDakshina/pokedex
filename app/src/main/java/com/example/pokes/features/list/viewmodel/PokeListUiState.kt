package com.example.pokes.features.list.viewmodel

import com.example.pokes.navigation.Screens
import com.example.lib_domain.model.Poke

sealed class PokeListUiState {
    data object Loading : PokeListUiState()
    data class Success(val data: List<Poke>) : PokeListUiState()
    data object Error : PokeListUiState()
}

sealed class ListScreenIntent {
    data class NavigateToDetail(val screen: Screens) : ListScreenIntent()
}

sealed class ListScreenEvent {
    data object OnInitialLoad : ListScreenEvent()
    data class OnPokeSelected(val Id: String) : ListScreenEvent()
}