package com.mehedi.k_movie_db.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mehedi.k_movie_db.api.ApiServices
import com.mehedi.k_movie_db.data.dto.MovieResult

class UpcomingPagingSource(private val api: ApiServices) :
    PagingSource<Int, MovieResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {

        return try {

            val position = params.key ?: 1
            val response = api.getUpcomingMovies("en-US", position)

            LoadResult.Page(
                data = response.movieResults,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

//        if (state.anchorPosition != null) {
//            val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
//            if (anchorPage?.prevKey != null) {
//                return anchorPage.prevKey!!.plus(1)
//
//            } else if (anchorPage?.nextKey != null) {
//
//                return anchorPage.nextKey!!.minus(1)
//            }
//
//        } else {
//            return null
//        }
//
//        return null
    }
}
