package com.pantaleon.imb_test.data

import androidx.lifecycle.MutableLiveData
import com.pantaleon.imb_test.data.model.Movie
import com.pantaleon.imb_test.network.MovieApi
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi) {

    fun getMovies(year: Int): MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        runBlocking {
            data.value = movieApi.findMoviesByYear(year, "popularity.desc").results
            // TODO: Handle error case
            data.value?.forEach(::println)
//            data.value?.forEach { Log.d("HTTPGET", it.toString()) }
        }

        return data
    }
}