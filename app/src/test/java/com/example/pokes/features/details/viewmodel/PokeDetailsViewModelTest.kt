package com.example.pokes.features.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.example.lib_domain.ResultType
import com.example.lib_domain.ResultType.Success
import com.example.lib_domain.model.PokeDetail
import com.example.lib_domain.usecases.PokeDetailsUseCase
import com.example.pokes.features.details.viewModel.PokeDetailsUiState
import com.example.pokes.features.details.viewModel.PokeDetailsViewModel
import com.example.pokes.navigation.Screens.PokeDetails
import com.example.pokesule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokeDetailsViewModelTest {

    @get: Rule
    val dispatcherRule = MainDispatcherRule()

    private val mockPokeDetailsUseCase = mockk<PokeDetailsUseCase>()
    private val savedStateHandle = mockk<SavedStateHandle>()

    private lateinit var subject: PokeDetailsViewModel

    @Before
    fun setUp() {
        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { savedStateHandle.toRoute<PokeDetails>() } returns PokeDetails(id = NAME)
        subject = PokeDetailsViewModel(mockPokeDetailsUseCase, savedStateHandle)
    }

    @Test
    fun `GIVEN initial state WHEN ViewModel is created THEN state is Loading`() = runTest {
        coEvery { mockPokeDetailsUseCase(any()) } returns Success(PokeDetail(NAME))
        subject.state.test {
            assertEquals(PokeDetailsUiState.Loading, awaitItem())
        }
    }

    @Test
    fun `GIVEN use case returns success WHEN ViewModel is created THEN state is Success`() =
        runTest {
            val pokeDetail = PokeDetail(NAME)
            coEvery { mockPokeDetailsUseCase(any()) } returns Success(pokeDetail)

            subject.getDetails()

            subject.state.test {
                assertEquals(PokeDetailsUiState.Success(pokeDetail), awaitItem())
            }
        }

    @Test
    fun `GIVEN use case returns error WHEN ViewModel is created THEN state is Error`() = runTest {
        coEvery { mockPokeDetailsUseCase(any()) } returns ResultType.Error(Exception(ERROR))

        subject.getDetails()

        subject.state.test {
            assertEquals(PokeDetailsUiState.Error, awaitItem())
        }
    }

    companion object {
        private const val NAME = " Poke 1"
        private const val ERROR = "Error"
    }
}