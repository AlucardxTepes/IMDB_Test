package com.pantaleon.imb_test.data

import androidx.lifecycle.MutableLiveData
import com.pantaleon.imb_test.data.model.Movie
import com.pantaleon.imb_test.network.MovieApi
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi) {

    fun getMovies(year: Int, sorting: String = "popularity"): MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        runBlocking {
            // Default sorting by popularity
            data.value = movieApi.findMoviesByYear(year, "${sorting.toLowerCase()}.desc").results
            // TODO: Handle error case
            data.value?.forEach(::println)
        }
        return data
    }

    fun search(query: String): MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        runBlocking {
            data.value = movieApi.searchMovies(query).results
            // TODO: Handle error case
            data.value?.forEach(::println)
        }
        return data
    }
}