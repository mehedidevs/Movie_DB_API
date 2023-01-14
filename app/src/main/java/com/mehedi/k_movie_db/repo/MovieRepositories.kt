package com.mehedi.k_movie_db.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mehedi.k_movie_db.api.ApiServices
import com.mehedi.k_movie_db.data.dto.latest.ResponseLatestMovieDTO
import com.mehedi.k_movie_db.paging.UpcomingPagingSource
import javax.inject.Inject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieRepositories @Inject constructor(private val apiServices: ApiServices) {

    fun getUpcomingMovie() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { UpcomingPagingSource(apiServices) }
    ).liveData

    private var _latestMovie = MutableLiveData<ResponseLatestMovieDTO>()
    val latestMovie: LiveData<ResponseLatestMovieDTO>
        get() = _latestMovie

    fun getLatestMovie() {

        GlobalScope.launch {
            val response = apiServices.getLatestMovie()
            if (response.isSuccessful) {
                _latestMovie.postValue(response.body())
            }
        }
    }
}
