package com.example.lib_domain.usecases

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.Poke
import com.example.lib_domain.repository.PokesListRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PokeListUseCaseTest {

    private val mockRepository = mockk<PokesListRepository>()
    private val subject = PokesListUseCase(mockRepository)

    @Test
    fun `GIVEN Success result type WHEN getPokesList is called THEN pokes list is returned`() =
        runTest {
            val pokeResult = ResultType.Success(emptyList<Poke>())
            coEvery { mockRepository.getPokesList() } returns pokeResult

            val result = subject.invoke()
            assertEquals(pokeResult, result)
        }
}