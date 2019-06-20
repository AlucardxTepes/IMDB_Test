package com.pantaleon.imb_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pantaleon.imb_test.data.MovieRepository
import java.util.*

class MoviesViewModel(val movieRepository: MovieRepository) : ViewModel() {

    private val _index = MutableLiveData<Int>()
    var movies = movieRepository.getMovies(Calendar.getInstance().get(Calendar.YEAR))

    val text: LiveData<String> = Transformations.map(_index) {
        "Viendo tab numero: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}