package com.pantaleon.imb_test.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pantaleon.imb_test.data.MovieRepository
import java.util.*

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _index = MutableLiveData<Int>()
    var movies = movieRepository.getMovies(Calendar.getInstance().get(Calendar.YEAR))

    // This value is set from the activity's menu item dialog "sort". Changing it will trigger the sortBy function
    private var currentSorting = "popularity"

    fun sortBy(sorting: String) {
        if (currentSorting.toLowerCase() != sorting.toLowerCase()) {
            // Convert "Rating" to vote_average, "Release Date" to "release_date" to match API format
            currentSorting = sorting.toLowerCase()
            when (sorting) {
                "rating" -> currentSorting = "vote_average"
                "release date" -> currentSorting = "release_date"
            }
            movies.value = movieRepository.getMovies(Calendar.getInstance().get(Calendar.YEAR), currentSorting).value
        } else {
            // Already sorted
        }
    }
}