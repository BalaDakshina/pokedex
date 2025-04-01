package com.example.lib_domain.usecases

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.PokeDetail
import com.example.lib_domain.repository.PokeDetailsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PokeDetailsUseCaseTest {

    private val mockRepository = mockk<PokeDetailsRepository>()
    private val subject = PokeDetailsUseCase(mockRepository)

    @Test
    fun `GIVEN Success result type WHEN getPokeDetails is called THEN detail is returned`() =
        runTest {
            val pokeName = "id"
            val detailsList = ResultType.Success(PokeDetail())
            coEvery { mockRepository.getPokeDetails(pokeName) } returns detailsList

            val result = subject.invoke(pokeName)
            assertEquals(detailsList, result)
        }
}