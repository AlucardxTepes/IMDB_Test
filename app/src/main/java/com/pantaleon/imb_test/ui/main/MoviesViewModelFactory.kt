package com.pantaleon.imb_test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pantaleon.imb_test.data.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesViewModelFactory @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MoviesViewModel(movieRepository) as T
    }

}