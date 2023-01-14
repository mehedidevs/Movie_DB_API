package com.mehedi.k_movie_db.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mehedi.k_movie_db.repo.MovieRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(private var repositories: MovieRepositories) :
    ViewModel() {

    val upcomingMovies = repositories.getUpcomingMovie().cachedIn(viewModelScope)
    val latestMoviesVMLD = repositories.latestMovie
    fun latestMovieVM() = repositories.getLatestMovie()
}
