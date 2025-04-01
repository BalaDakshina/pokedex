package com.example.lib_data.mapper

import com.example.lib_data.model.Home
import com.example.lib_data.model.Other
import com.example.lib_data.model.PokeDetailsResponse
import com.example.lib_data.model.Sprites
import com.example.lib_data.model.Stat
import com.example.lib_data.model.Type
import com.example.lib_domain.ResultType
import com.example.lib_domain.model.PokeDetail
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PokeDetailsMapperTest {

    private val subject = PokeDetailsMapper()

    @Test
    fun `GIVEN pokeDetailsResponse WHEN map is called THEN returns pokeDetail`() {
        val response = ResultType.Success(
            PokeDetailsResponse(
                height = 100,
                name = NAME,
                sprites = Sprites(other = Other(home = Home(frontDefault = "front_default_url"))),
                stats = listOf(Stat(baseStat = 50, effort = 1, stat = Type(name = STAT_ATTACK))),
                weight = 200
            )
        )
        val expected = ResultType.Success(
            PokeDetail(
                name = NAME,
                imageUrl = "front_default_url",
                height = 100.toString(),
                weight = 200.toString(),
                baseStats = listOf(
                    com.example.lib_domain.model.Stat(name = STAT_ATTACK, amount = "50")
                ),
            )
        )
        val result = subject.map(response)

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN pokeDetailsResponse with empty fields WHEN map is called THEN returns pokeDetail with empty fields`() {
        val response = ResultType.Success(
            PokeDetailsResponse(
                height = 0,
                name = EMPTY_STRING,
                sprites = Sprites(other = Other(home = Home(frontDefault = EMPTY_STRING))),
                stats = emptyList(),
                weight = 0
            )
        )
        val expected = ResultType.Success(
            PokeDetail(
                name = EMPTY_STRING,
                imageUrl = EMPTY_STRING,
                height = 0.toString(),
                weight = 0.toString(),
                baseStats = emptyList()
            )
        )

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
        const val NAME = " Name"
        const val STAT_ATTACK = "Attack"
        const val EMPTY_STRING = ""
    }
}