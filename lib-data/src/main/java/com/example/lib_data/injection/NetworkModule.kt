package com.example.lib_data.injection

import com.example.lib_data.services.PokesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val client = OkHttpClient().newBuilder()
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okhttp: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun providePokesService(retrofit: Retrofit): PokesService {
        return retrofit.create<PokesService>()
    }

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}