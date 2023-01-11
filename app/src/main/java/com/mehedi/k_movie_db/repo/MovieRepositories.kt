package com.mehedi.k_movie_db.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mehedi.k_movie_db.api.ApiServices
import com.mehedi.k_movie_db.paging.UpcomingPagingSource
import javax.inject.Inject

class MovieRepositories @Inject constructor(private val apiServices: ApiServices) {

    fun getUpcomingMovie() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { UpcomingPagingSource(apiServices) }
    ).liveData


}
