package com.example.lesson62.data.remote.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lesson62.data.model.Data
import com.example.lesson62.data.remote.RetrofitClient
import com.example.lesson62.data.remote.apiservice.KitsuApiService
import retrofit2.HttpException
import java.io.IOException

private const val LIMIT = 20

class AnimePagingSource : PagingSource<Int, Data>() {
    private val apiService: KitsuApiService = RetrofitClient.apiService
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        val pageSize = params.loadSize
        val position = params.key ?: 1
        return try {
//            val response = apiService.getAnime(LIMIT, position).execute()
//            val data = response.body()?.data ?: emptyList()
//            LoadResult.Page(
//                data = data,
//                prevKey = if (position == 1) null else position - 1,
//                nextKey = if (data.isEmpty()) null else position + 1
//            )
            val response = apiService.getAnime(limit = pageSize, offset = position)
            val nextPage =
                Uri.parse(response.links.next).getQueryParameter("page[offset]")!!.toInt()
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = nextPage
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
