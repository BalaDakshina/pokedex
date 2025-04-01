package com.example.lib_data.mapper

import com.example.lib_data.model.PokeDetailsResponse
import com.example.lib_domain.ResultType
import com.example.lib_domain.asResult
import com.example.lib_domain.model.PokeDetail
import com.example.lib_domain.model.Stat
import javax.inject.Inject

class PokeDetailsMapper @Inject constructor() {
    fun map(response: ResultType<PokeDetailsResponse>): ResultType<PokeDetail> =
        response.asResult { data ->
            PokeDetail(
                name = data.name,
                imageUrl = data.sprites.other.home.frontDefault,
                height = data.height.toString(),
                weight = data.weight.toString(),
                baseStats = data.stats.map {
                    Stat(
                        name = it.stat.name,
                        amount = it.baseStat.toString()
                    )
                }
            )
        }
}
