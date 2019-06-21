package com.pantaleon.imb_test.ui.main.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pantaleon.imb_test.data.MovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesViewModelFactory @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return FavoritesViewModel(movieRepository) as T
    }

}