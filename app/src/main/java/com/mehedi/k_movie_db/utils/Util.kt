package com.mehedi.k_movie_db.utils

// buildImageUrl(movie.posterPath!!)

object Util {

    fun posterUrlMake(uri: String?): String {
        return "https://image.tmdb.org/t/p/w780$uri"
    }
}
