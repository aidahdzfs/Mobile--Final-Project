package com.D121211037.filmapp.data

import com.D121211037.filmapp.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val filmsRepository: FilmRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://api.themoviedb.org"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val filmsRepository: FilmRepository
        get() = FilmRepository(retrofitService)
}