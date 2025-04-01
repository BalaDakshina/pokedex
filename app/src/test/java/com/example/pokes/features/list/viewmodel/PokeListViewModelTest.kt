package com.example.pokes.features.list.viewmodel

import app.cash.turbine.test
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.Poke
import com.example.lib_domain.usecases.PokesListUseCase
import com.example.pokesule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokeListViewModelTest {
    @get: Rule
    val dispatcherRule = MainDispatcherRule()

    private val mockPokesListUseCase = mockk<PokesListUseCase>()
    private lateinit var subject: PokeListViewModel

    @Before
    fun setUp() {
        subject = PokeListViewModel(mockPokesListUseCase)
    }

    @Test
    fun `GIVEN initial state WHEN ViewModel is created THEN state is Loading`() = runTest {
        subject.state.test {
            assertEquals(PokeListUiState.Loading, awaitItem())
        }
    }

    @Test
    fun `GIVEN use case returns success WHEN ViewModel is created THEN state is Success`() =
        runTest {
            val pokes = listOf(Poke(POKE_NAME))
            coEvery { mockPokesListUseCase() } returns ResultType.Success(pokes)

            subject.getPokesList()

            subject.state.test {
                assertEquals(PokeListUiState.Success(pokes), awaitItem())
            }
        }

    @Test
    fun `GIVEN use case returns error WHEN ViewModel is created THEN state is Error`() = runTest {
        coEvery { mockPokesListUseCase() } returns ResultType.Error(Exception(ERROR))

        subject.getPokesList()

        subject.state.test {
            assertEquals(PokeListUiState.Error, awaitItem())
        }
    }

    companion object {
        private const val POKE_NAME = "Poke 1"
        private const val ERROR = "Error"
    }
}