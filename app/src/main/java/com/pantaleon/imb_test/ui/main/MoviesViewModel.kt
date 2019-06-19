package com.pantaleon.imb_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pantaleon.imb_test.data.MovieRepository
import com.pantaleon.imb_test.data.model.Movie
import java.util.*

class MoviesViewModel(movieRepository: MovieRepository) : ViewModel() {

    private val _index = MutableLiveData<Int>()
    private val _movies = movieRepository.getMovies(Calendar.getInstance().get(Calendar.YEAR))

    val movies: LiveData<List<Movie>> = _movies
    val text: LiveData<String> = Transformations.map(_index) {
        "Viendo tab numero: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}