package com.pantaleon.imb_test.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.pantaleon.imb_test.network.MovieApi
import com.pantaleon.imb_test.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

const val IMDB_BASE_URL = "https://api.themoviedb.org/3/"
const val IMDB_BASE_IMAGE_URL = "https://image.tmdb.org/t/p"

@Module
class NetworkModule(val application: Application) {

    @Reusable
    @Provides
    fun provideTokenInterceptor(): TokenInterceptor = TokenInterceptor()

    @Reusable
    @Provides
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(tokenInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Reusable
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(IMDB_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
            .client(okHttpClient)
            .build()

    @Reusable
    @Provides
    fun provideMovieApi(rf: Retrofit) = rf.create(MovieApi::class.java)

    @Reusable
    @Provides
    fun provideConnectivityManager(): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}