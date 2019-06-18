package com.pantaleon.imb_test.network

import com.pantaleon.imb_test.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * This interceptor includes the API_KEY with every HTTP request (Required to use the IMDB API)
 */
class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.IMDB_API_TOKEN)
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }


}