package com.example.lesson62.data.model

import com.google.gson.annotations.SerializedName

data class MangaResponse(
    @SerializedName("links")
    val links: Links,
    @SerializedName("data")
    val data: List<Data>
)