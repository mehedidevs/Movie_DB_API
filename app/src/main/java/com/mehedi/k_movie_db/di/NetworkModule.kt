package com.mehedi.k_movie_db.di

import com.mehedi.k_movie_db.api.ApiServices
import com.mehedi.k_movie_db.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit.Builder {

        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    }

    @Provides
    @Singleton
    fun providesApi(builder: Retrofit.Builder): ApiServices {
        return builder.build().create(ApiServices::class.java)
    }




}