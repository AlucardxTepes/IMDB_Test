package com.pantaleon.imb_test.ui.main.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pantaleon.imb_test.R
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
        Log.d("Detail Activity", "Movie ID: $movieId")
    }
}
