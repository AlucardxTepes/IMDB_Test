package com.pantaleon.imb_test.network

import com.pantaleon.imb_test.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    /**
     * Returns list of movies for a given year.
     * Results can be sorted by property either desc or asc
     */
    @GET("discover/movie")
    suspend fun findMoviesByYear(@Query("primary_release_year") year: Int,
                         @Query("sort_by") sortProperty: String): MovieListResponse

    // TODO: Add movie detail endpoint

    // TODO: Add search by name endpoint
}