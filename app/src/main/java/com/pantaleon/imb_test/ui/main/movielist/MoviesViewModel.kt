package com.pantaleon.imb_test.ui.main.movielist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.pantaleon.imb_test.data.MovieRepository
import com.pantaleon.imb_test.data.model.Movie
import java.util.*

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    var movies = movieRepository.getMovies(Calendar.getInstance().get(Calendar.YEAR))
    var reloading = MediatorLiveData<Boolean>()

    // This value is set from the activity's menu item dialog "sort". Changing it will trigger the sortBy function
    private var currentSorting = "popularity"

    fun sortBy(sorting: String) {
        if (currentSorting.toLowerCase() != sorting.toLowerCase()) { // Avoid running if already sorted
            // Convert "Rating" to vote_average, "Release Date" to "release_date" to match API format
            currentSorting = sorting.toLowerCase()
            when (sorting) {
                "rating" -> currentSorting = "vote_average"
                "release date" -> currentSorting = "release_date"
            }
            movies.value = movieRepository.getMovies(Calendar.getInstance().get(Calendar.YEAR), currentSorting).value
        }
    }

    /**
     * Search for movies
     */
    fun search(query: String) {
        movies.value = movieRepository.search(query).value
    }

    /**
     * Sets movie as favorite
     */
    fun addFavoriteMovie(movie: Movie) {
        movieRepository.insertMovie(movie)
    }

    fun fetchData(forceRefresh: Boolean = false) {
        reloading.value = true
        movies.value = movieRepository.getMovies(Calendar.getInstance().get(Calendar.YEAR), currentSorting, forceRefresh).value
        reloading.value = false
    }
}