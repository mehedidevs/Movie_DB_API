package com.mehedi.k_movie_db.api

import com.mehedi.k_movie_db.data.dto.ResponseUpcomingMovie
import com.mehedi.k_movie_db.data.dto.latest.ResponseLatestMovieDTO
import com.mehedi.k_movie_db.utils.Constant.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("3/movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): ResponseUpcomingMovie

    @GET("3/movie/latest?api_key=$API_KEY")
    suspend fun getLatestMovie(): Response<ResponseLatestMovieDTO>
}
