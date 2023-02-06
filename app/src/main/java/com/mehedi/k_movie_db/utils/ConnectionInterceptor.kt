package com.mehedi.k_movie_db.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class ConnectionInterceptor
@Inject constructor(@ApplicationContext var context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isOnline(context)) {
            throw NoInternetException("Please Check You Internet Connection !")
        } else {
            return chain.proceed(chain.request().newBuilder().build())
        }
    }

    private fun isOnline(context: Context): Boolean {
        var connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var networkInfo: NetworkInfo? = connectionManager.activeNetworkInfo

        return networkInfo?.isConnected ?: false
    }
}
