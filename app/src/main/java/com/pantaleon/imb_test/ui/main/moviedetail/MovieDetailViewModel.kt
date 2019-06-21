package com.pantaleon.imb_test.ui.main.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pantaleon.imb_test.data.MovieRepository
import com.pantaleon.imb_test.data.model.Movie

class MovieDetailViewModel(
    movieRepository: MovieRepository,
    movieId: Int
) : ViewModel() {

    var movie = MutableLiveData<Movie>()
//    var title = MutableLiveData<String>()
//    var overview = MutableLiveData<String>()
//    var rating = MutableLiveData<Float>()
//    var releaseDate = MutableLiveData<String>()
//    var backdropPath = MutableLiveData<String>()
//    var voteCount = MutableLiveData<Int>()
//    var popularity = MutableLiveData<Float>()

    init {
        movie.value = movieRepository.findOne(movieId)
//            title.value = it.title
//            overview.value = it.overview
//            rating.value = it.voteAverage
//            releaseDate.value = it.releaseDate
//            backdropPath.value = it.backdropPath
//            voteCount.value = it.voteCount
//            popularity.value = it.popularity
//        }

    }
}