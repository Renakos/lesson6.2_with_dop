package com.example.lesson62.data.model

import com.google.gson.annotations.SerializedName

data class MangaCharacters(
    @SerializedName("links")
    val links: Links
)