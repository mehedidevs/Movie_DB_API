package com.mehedi.k_movie_db.di

import com.mehedi.k_movie_db.api.ApiServices
import com.mehedi.k_movie_db.utils.ConnectionInterceptor
import com.mehedi.k_movie_db.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHTTP(connectionInterceptor: ConnectionInterceptor): OkHttpClient {

        return OkHttpClient.Builder().addInterceptor(connectionInterceptor)
            .connectTimeout(1, TimeUnit.MILLISECONDS)
            .writeTimeout(1, TimeUnit.MILLISECONDS)
            .readTimeout(1, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun providesApi(builder: Retrofit.Builder, okHttpClient: OkHttpClient): ApiServices {
        return builder.client(okHttpClient).build().create(ApiServices::class.java)
    }
}
