package com.mehedi.k_movie_db.utils

sealed class DataState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>() : DataState<T>()
    class Success<T>(data: T) : DataState<T>(data)
    class Error<T>(message: String) : DataState<T>(message = message)
}
