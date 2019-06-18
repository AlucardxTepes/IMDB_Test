package com.pantaleon.imb_test.model

data class Movie(val id: Long,
                 val title: String,
                 val posterPath: String,
                 val releaseDate: String,
                 val overview: String,
                 val voteAverage: Float,
                 val voteCount: Int)