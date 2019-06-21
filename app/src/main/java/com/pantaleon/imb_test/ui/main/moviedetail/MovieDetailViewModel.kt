package com.pantaleon.imb_test.ui.main.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pantaleon.imb_test.data.MovieRepository
import com.pantaleon.imb_test.data.model.Movie

class MovieDetailViewModel(
    private val movieRepository: MovieRepository,
    movieId: Int
) : ViewModel() {

    var movie = MutableLiveData<Movie>()
    var isFavorite = MutableLiveData<Boolean>()

    init {
        movie.value = movieRepository.findOne(movieId)
        isFavorite.value = movie.value?.isFavorite
    }

    /**
     * Toggle movie favorite state
     */
    fun toggleFavorite() {
        movie.value?.isFavorite = !movie.value?.isFavorite!!
        isFavorite.value = movie.value?.isFavorite
        movieRepository.insertMovie(movie.value!!)
    }
}