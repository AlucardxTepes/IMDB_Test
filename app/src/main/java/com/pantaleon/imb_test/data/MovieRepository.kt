package com.pantaleon.imb_test.data

import android.net.ConnectivityManager
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
    private val movieDao: MovieDao,
    private val connectivityManager: ConnectivityManager
) {

    /**
     * Retrieves latest movies for current year, sorted by popularity by default
     */
    fun getMovies(year: Int, sorting: String = "popularity"): MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()

        runBlocking {
            if (isDbEmpty() && isNetworkAvailable()) { // TODO Check if local data is stale
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
                println("========= Fetching data from LOCAL DATABASE sort by ${sorting.toLowerCase()}============")
                data.value = movieDao.getMovies(year, sorting.toLowerCase())
                data.value?.forEach(::println)
            }
        }

        return data
    }

    fun findOne(movieId: Int): Movie {
        println("========= Fetching data from LOCAL DATABASE movie ID: $movieId ============")
        return runBlocking {
            movieDao.findById(movieId)
        }
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
     * Check if internet connectivity is available
     */
    private fun isNetworkAvailable(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
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

    fun insertMovie(movie: Movie) {
        runBlocking { movieDao.insert(movie) }
    }

    /**
     * Gets list of movies marked as favorite
     */
    fun getFavoriteMovies(): MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()
        runBlocking {
            println("========= Fetching data from LOCAL DATABASE favorites only ============")
            data.value = movieDao.getMarkedAsFavorite()
            data.value?.forEach(::println)
        }
        return data
    }
}