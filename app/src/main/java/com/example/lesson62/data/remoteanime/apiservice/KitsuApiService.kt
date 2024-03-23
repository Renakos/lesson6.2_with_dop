package com.example.lesson62.data.remoteanime.apiservice

import com.example.lesson62.data.model.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val CHARACTERS_END_POINT = "edge/anime"

interface KitsuApiService {

    @GET(CHARACTERS_END_POINT)
    suspend fun getAnime(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int
    ): MangaResponse

}