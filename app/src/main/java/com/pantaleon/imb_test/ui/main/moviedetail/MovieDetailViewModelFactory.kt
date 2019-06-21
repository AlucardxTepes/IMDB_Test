package com.pantaleon.imb_test.ui.main.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pantaleon.imb_test.data.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailViewModelFactory @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {

    var id: Int? = null

    fun setMovieId(id: Int) {
        this.id = id
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MovieDetailViewModel(movieRepository, id!!) as T
    }

}