package com.pantaleon.imb_test.di

import android.app.Application
import androidx.room.Room
import com.pantaleon.imb_test.data.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val application: Application) {


    @Singleton
    @Provides
    fun provideMovieDatabase() : MovieDatabase = Room.databaseBuilder(application, MovieDatabase::class.java, "movie-db").build()


    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase) = database.movieDao()
}