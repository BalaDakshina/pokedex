package com.example.lib_data.repository

import com.example.lib_data.mapper.PokeDetailsMapper
import com.example.lib_data.model.PokeDetailsResponse
import com.example.lib_data.services.PokesService
import com.example.lib_data.util.CoroutineDispatchersProvider
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.PokeDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class PokeDetailsRepositoryImplTest {

    private val mockService = mockk<PokesService>()
    private val mockMapper = mockk<PokeDetailsMapper>()
    private val dispatcher = mockk<CoroutineDispatchersProvider>()

    private val subject = PokeDetailsRepositoryImpl(mockService, mockMapper, dispatcher)

    @Test
    fun `GIVEN successful API call WHEN getDetails is called THEN details is returned`() =
        runTest {
            val mockDetailsResponse = mockk<PokeDetailsResponse>()
            val mockResponse = Response.success(mockDetailsResponse)
            val mockResult = ResultType.Success(mockDetailsResponse)
            val mockDetails = mockk<PokeDetail>()
            coEvery { mockService.getPokeDetails(ID) } returns mockResponse
            every { mockMapper.map(mockResult) } returns ResultType.Success(mockDetails)
            every { dispatcher.default } returns Dispatchers.Unconfined

            val result = subject.getPokeDetails(ID)

            assertEquals(ResultType.Success(mockDetails), result)
            coVerify {
                mockService.getPokeDetails(ID)
                mockMapper.map(mockResult)
            }
        }

    @Test
    fun `GIVEN un-successful API call WHEN getDetails is called THEN error is returned`() =
        runTest {
            val exception = Exception()
            val mockResult = ResultType.Error(exception)

            coEvery { mockService.getPokeDetails(ID) } throws exception
            every { mockMapper.map(mockResult) } returns ResultType.Error(exception)
            every { dispatcher.default } returns Dispatchers.Unconfined

            val result = subject.getPokeDetails(ID)

            assertEquals(ResultType.Error(exception), result)
            coVerify {
                mockService.getPokeDetails(ID)
                mockMapper.map(mockResult)
            }
        }

    companion object {
        private const val ID = "userId"
    }
}