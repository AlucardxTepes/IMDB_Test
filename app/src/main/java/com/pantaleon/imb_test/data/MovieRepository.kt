package com.pantaleon.imb_test.data

import androidx.lifecycle.MutableLiveData
import com.pantaleon.imb_test.data.model.Movie
import com.pantaleon.imb_test.network.MovieApi
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

const val DATA_STALE_TIME = 0 // TODO

@Singleton
class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) {

    /**
     * Retrieves latest movies for current year, sorted by popularity by default
     */
    fun getMovies(year: Int, sorting: String = "popularity"): MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()


        runBlocking {
            if (isDbEmpty()) { // TODO Check if local data is stale
                data.value = movieApi.findMoviesByYear(year, "${sorting.toLowerCase()}.desc").results
                // TODO: Handle error case and loading icon
                println("========= Fetching data from REMOTE API ============")
                data.value?.forEach(::println)

                // Store remote data into local db
                data.value?.forEach {
                    println("========= Storing fetched data into LOCAL DATABASE ============")
                    movieDao.insert(it)
                }
            } else {
                println("========= Fetching data from LOCAL DATABASE ============")
                data.value = movieDao.getMovies(year, sorting.toLowerCase())
                data.value?.forEach(::println)
            }
        }

        return data
    }

    fun search(query: String): MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        runBlocking {
            data.value = movieApi.searchMovies(query).results
            // TODO: Handle error case and loading icon
            data.value?.forEach(::println)
        }
        return data
    }

    /**
     * Perform a quick database check by retrieving a single item
     */
    private fun isDbEmpty(): Boolean {
        var result: Movie? = null
        runBlocking {
            result = movieDao.dbCheck()
        }
        return result == null
    }

    private fun isDataStale(): Boolean = true
}