package com.D121211037.filmapp.response

import com.D121211037.filmapp.models.Dates
import com.D121211037.filmapp.models.Film
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetFilmResponse(
    @SerialName("dates")
    val dates: Dates,
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<Film>?,
    @SerialName("total_pages")
    val total_pages: Int?,
    @SerialName("total_results")
    val total_results: Int?
)