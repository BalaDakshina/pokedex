package com.example.pokes.features.list.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.Poke
import com.example.lib_domain.usecases.PokesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
    private val pokesListUseCase: PokesListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PokeListUiState>(PokeListUiState.Loading)
    val state: StateFlow<PokeListUiState> = _state.asStateFlow()

    suspend fun getPokesList() {
        val result = pokesListUseCase()
        _state.value = mapResult(result)
    }

    private fun mapResult(result: ResultType<List<Poke>>) =
        when (result) {
            is ResultType.Success -> PokeListUiState.Success(result.data)
            is ResultType.Error -> PokeListUiState.Error
        }
}