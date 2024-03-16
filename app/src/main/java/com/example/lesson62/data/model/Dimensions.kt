package com.example.lesson62.data.model

import com.google.gson.annotations.SerializedName

data class Dimensions(
    @SerializedName("small")
    val small: Small,
    @SerializedName("large")
    val large: Large,
    @SerializedName("tiny")
    val tiny: Tiny
)