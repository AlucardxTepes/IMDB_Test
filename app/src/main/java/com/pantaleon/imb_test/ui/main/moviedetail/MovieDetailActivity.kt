package com.pantaleon.imb_test.ui.main.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.pantaleon.imb_test.R
import com.pantaleon.imb_test.di.IMDB_BASE_IMAGE_URL
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // retrieve args from navigation component
        val safeArgs: MovieDetailActivityArgs by navArgs()
        val movieId = safeArgs.movieId
        val movieTitle = safeArgs.movieTitle
        val movieBackdropPath = safeArgs.movieBackdropPath
        Log.d("Detail Activity", "Movie ID: $movieId\nMovieTitle: $movieTitle")

        if (movieBackdropPath.isEmpty()) {
            Glide.with(this)
                .load(getDrawable(R.drawable.movie_placeholder))
                .into(movieBackdrop)
        } else {
            Glide.with(this)
                .load("$IMDB_BASE_IMAGE_URL/w500$movieBackdropPath")
                .into(movieBackdrop)
        }
    }
}
