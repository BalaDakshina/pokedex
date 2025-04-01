package com.example.lib_data.mapper

import com.example.lib_data.model.PokesListResponse
import com.example.lib_data.model.Result
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.Poke
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PokeListMapperTest {

    private val subject = PokeListMapper()

    @Test
    fun `GIVEN ListResponse WHEN map is called THEN returns list of `() {
        val response = ResultType.Success(PokesListResponse(results = listOf(Result(name = NAME))))
        val expected = ResultType.Success(listOf(Poke(name = NAME)))

        val result = subject.map(response)

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN ListResponse with empty fields WHEN map is called THEN returns list of  with empty fields`() {
        val response =
            ResultType.Success(PokesListResponse(results = listOf(Result(name = EMPTY_STRING))))
        val expected = ResultType.Success(listOf(Poke(name = EMPTY_STRING)))

        val result = subject.map(response)

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN error response WHEN map is called THEN returns empty list`() {
        val response = ResultType.Error(Exception("Network error"))

        val result = subject.map(response)

        assertTrue(result is ResultType.Error)
    }

    companion object {
        const val EMPTY_STRING = ""
        const val NAME = "Name"
    }
}