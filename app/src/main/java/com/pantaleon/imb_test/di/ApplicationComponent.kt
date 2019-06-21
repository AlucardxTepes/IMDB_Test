package com.pantaleon.imb_test.di

import com.pantaleon.imb_test.ui.main.MainActivity
import com.pantaleon.imb_test.ui.main.favorites.FavoritesFragment
import com.pantaleon.imb_test.ui.main.moviedetail.MovieDetailActivity
import com.pantaleon.imb_test.ui.main.movielist.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MoviesFragment)
    fun inject(movieDetailActivity: MovieDetailActivity)
    fun inject(favoritesFragment: FavoritesFragment)
}