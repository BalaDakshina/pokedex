package com.example.pokes.features.details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.lib_domain.ResultType
import com.example.lib_domain.ResultType.Error
import com.example.lib_domain.ResultType.Success
import com.example.lib_domain.model.PokeDetail
import com.example.lib_domain.usecases.PokeDetailsUseCase
import com.example.pokes.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokeDetailsViewModel @Inject constructor(
    private val pokeDetailsUseCase: PokeDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<PokeDetailsUiState>(PokeDetailsUiState.Loading)
    val state: StateFlow<PokeDetailsUiState> = _state.asStateFlow()

    suspend fun getDetails() {
        val pokeName = savedStateHandle.toRoute<Screens.PokeDetails>().id
        val result = pokeDetailsUseCase(pokeName)
        _state.value = mapResult(result)
    }

    private fun mapResult(result: ResultType<PokeDetail>) =
        when (result) {
            is Success -> PokeDetailsUiState.Success(result.data)
            is Error -> PokeDetailsUiState.Error
        }
}