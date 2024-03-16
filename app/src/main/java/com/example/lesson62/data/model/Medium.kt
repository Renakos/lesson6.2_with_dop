package com.example.lesson62.data.model

import com.google.gson.annotations.SerializedName

data class Medium(
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("height")
    val height: Int? = null
)