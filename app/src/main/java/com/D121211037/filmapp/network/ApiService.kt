package com.D121211037.filmapp.network


import com.D121211037.filmapp.response.GetFilmResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyYjUzMGY3ZmZlNjJiMWZmMWViZjQxZjBiY2IzNmY4NyIsInN1YiI6IjYzZTcxOGFjZDM4OGFlMDA4MDYxMzRhYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ovnSfV4u35atvWS0qUpW3Ef1zDbk41fBPzc-y6cwmao"
    )
    @GET("3/movie/now_playing")
    suspend fun getFilms(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): GetFilmResponse
}