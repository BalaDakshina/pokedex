package com.example.lib_data.injection

import com.example.lib_data.repository.PokeDetailsRepositoryImpl
import com.example.lib_data.repository.PokesListRepositoryImpl
import com.example.lib_domain.repository.PokeDetailsRepository
import com.example.lib_domain.repository.PokesListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsListRepository(pokesListRepositoryImpl: PokesListRepositoryImpl): PokesListRepository

    @Binds
    abstract fun bindsDetailsRepository(pokeDetailsRepositoryImpl: PokeDetailsRepositoryImpl): PokeDetailsRepository
}