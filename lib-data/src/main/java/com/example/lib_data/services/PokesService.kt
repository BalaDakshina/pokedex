package com.example.lib_data.services

import com.example.lib_data.model.PokeDetailsResponse
import com.example.lib_data.model.PokesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokesService {
    @GET("pokemon")
    suspend fun getPokesList(): Response<PokesListResponse>

    @GET("pokemon/{id}")
    suspend fun getPokeDetails(@Path("id") id: String): Response<PokeDetailsResponse>
}