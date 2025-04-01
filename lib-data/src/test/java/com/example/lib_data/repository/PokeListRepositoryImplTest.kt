package com.example.lib_data.repository

import com.example.lib_data.mapper.PokeListMapper
import com.example.lib_data.model.PokesListResponse
import com.example.lib_data.services.PokesService
import com.example.lib_data.util.CoroutineDispatchersProvider
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.Poke
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class PokeListRepositoryImplTest {

    private val mockService = mockk<PokesService>()
    private val mockMapper = mockk<PokeListMapper>()
    private val dispatcher = mockk<CoroutineDispatchersProvider>()

    private val subject = PokesListRepositoryImpl(mockService, mockMapper, dispatcher)

    @Test
    fun `GIVEN successful API call WHEN getPokesList is called THEN list is returned`() =
        runTest {
            val mockListsResponse = mockk<PokesListResponse>()
            val mockResponse = Response.success(mockListsResponse)
            val mockResult = ResultType.Success(mockListsResponse)
            val mockList = mockk<Poke>()
            coEvery { mockService.getPokesList() } returns mockResponse
            every { mockMapper.map(mockResult) } returns ResultType.Success(listOf(mockList))
            every { dispatcher.default } returns Dispatchers.Unconfined

            val result = subject.getPokesList()

            assertEquals(ResultType.Success(listOf(mockList)), result)
            coVerify {
                mockService.getPokesList()
                mockMapper.map(mockResult)
            }
        }

    @Test
    fun `GIVEN un-successful API call WHEN getPokesList is called THEN error is returned`() =
        runTest {
            val exception = Exception()
            val mockResult = ResultType.Error(exception)

            coEvery { mockService.getPokesList() } throws exception
            every { mockMapper.map(mockResult) } returns ResultType.Error(exception)
            every { dispatcher.default } returns Dispatchers.Unconfined

            val result = subject.getPokesList()

            assertEquals(ResultType.Error(exception), result)
            coVerify {
                mockService.getPokesList()
                mockMapper.map(mockResult)
            }
        }
}