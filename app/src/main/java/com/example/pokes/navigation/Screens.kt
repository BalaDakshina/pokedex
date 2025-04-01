package com.example.pokes.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object PokeList : Screens()

    @Serializable
    data class PokeDetails(val id: String) : Screens()
}