package com.pantaleon.imb_test.ui.main

import androidx.lifecycle.ViewModel
import com.pantaleon.imb_test.data.MovieRepository
import java.util.*

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    var movies = movieRepository.getMovies(Calendar.getInstance().get(Calendar.YEAR))

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
}