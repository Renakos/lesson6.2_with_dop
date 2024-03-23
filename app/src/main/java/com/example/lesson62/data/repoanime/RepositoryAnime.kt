package com.example.lesson62.data.repoanime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.lesson62.data.remoteanime.apiservice.KitsuApiService
import com.example.lesson62.data.remoteanime.paging.AnimePagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class RepositoryAnime(private val apiService: KitsuApiService) {

    fun getAnime() = Pager(
        PagingConfig(
            pageSize = 15,
            initialLoadSize = 15
        )
    ) {
        AnimePagingSource()
    }.flow.flowOn(Dispatchers.IO)
}
