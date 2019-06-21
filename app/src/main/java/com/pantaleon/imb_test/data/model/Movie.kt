package com.pantaleon.imb_test.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Data class that works as a local database Entity as well
 * as a DTO to be mapped by Jackson when retrieving remote JSON data
 */
@Entity
data class Movie(
    @PrimaryKey val id: Long,
    val title: String,
    val overview: String,
    val popularity: Float,
    @JsonProperty("poster_path") val posterPath: String?,
    @JsonProperty("release_date") val releaseDate: String,
    @JsonProperty("vote_average") val voteAverage: Float,
    @JsonProperty("vote_count") val voteCount: Int,
    @JsonProperty("backdrop_path") val backdropPath: String?
)