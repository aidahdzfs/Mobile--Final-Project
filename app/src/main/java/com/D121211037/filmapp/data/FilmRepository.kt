package com.D121211037.filmapp.data

import com.D121211037.filmapp.network.ApiService
import com.D121211037.filmapp.response.GetFilmResponse

class FilmRepository(private val apiService: ApiService) {

    suspend fun getFilms(): GetFilmResponse {
        return apiService.getFilms()
    }
}