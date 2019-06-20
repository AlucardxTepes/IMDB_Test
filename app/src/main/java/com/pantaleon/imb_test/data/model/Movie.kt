package com.pantaleon.imb_test.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Movie(
    val id: Long,
    val title: String,
    @JsonProperty("poster_path") val posterPath: String,
    @JsonProperty("release_date") val releaseDate: String,
    val overview: String,
    @JsonProperty("vote_average") val voteAverage: Float,
    @JsonProperty("vote_count") val voteCount: Int,
    @JsonProperty("backdrop_path") val backdropPath: String
)