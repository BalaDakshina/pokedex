package com.example.lib_domain.repository

import com.example.lib_domain.ResultType
import com.example.lib_domain.model.PokeDetail

interface PokeDetailsRepository {
    suspend fun getPokeDetails(id: String): ResultType<PokeDetail>
}