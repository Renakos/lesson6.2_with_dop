package com.example.lesson62.data.model

import com.google.gson.annotations.SerializedName

data class MediaRelationships(
    @SerializedName("links")
    val links: Links
)