package com.D121211037.filmapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Dates(
    val maximum: String,
    val minimum: String
) : Parcelable