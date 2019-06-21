package com.pantaleon.imb_test.ui.main.favorites

import androidx.lifecycle.ViewModel
import com.pantaleon.imb_test.data.MovieRepository
import com.pantaleon.imb_test.data.model.Movie

class FavoritesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    var movies = movieRepository.getFavoriteMovies()

    /**
     * Sets movie as favorite
     */
    fun addFavoriteMovie(movie: Movie) {
        movieRepository.insertMovie(movie)
    }

    fun fetchData() {
        movies.value = movieRepository.getFavoriteMovies().value
    }
}