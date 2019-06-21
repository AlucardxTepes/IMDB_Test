package com.pantaleon.imb_test.ui.main.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.pantaleon.imb_test.MoviesApp
import com.pantaleon.imb_test.R
import com.pantaleon.imb_test.databinding.ActivityMovieDetailBinding
import com.pantaleon.imb_test.di.IMDB_BASE_IMAGE_URL
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var viewModelFactory: MovieDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMovieDetailBinding>(this, R.layout.activity_movie_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inject Dagger Dependencies
        MoviesApp.getAppComponent(this).inject(this)

        // retrieve args from navigation component
        val safeArgs: MovieDetailActivityArgs by navArgs()
        val movieId = safeArgs.movieId
        val movieTitle = safeArgs.movieTitle
        val movieBackdropPath = safeArgs.movieBackdropPath
        Log.d("Detail Activity", "Movie ID: $movieId\nMovieTitle: $movieTitle")

        // Init viewModel
        viewModelFactory.id = movieId
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        viewModel.movie.observe(this, Observer {
            if (movieBackdropPath.isEmpty()) {
                Glide.with(this)
                    .load(getDrawable(R.drawable.movie_placeholder))
                    .into(movieBackdrop)
            } else {
                Glide.with(this)
                    .load("$IMDB_BASE_IMAGE_URL/w500${it.backdropPath}")
                    .into(movieBackdrop)
            }
            supportActionBar?.title = it.title
            overview.text = it.overview
            voteCount.text = "Total Votes: ${it.voteCount}"
            popularity.text = "Popularity: ${it.popularity}"
            releaseDate.text = "Release Date: ${it.releaseDate}"
            ratingBar.rating = it.voteAverage/2

        })
        // TODO: Make FAB save movie as favorite
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
