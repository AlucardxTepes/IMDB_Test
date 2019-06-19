package com.pantaleon.imb_test.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieListResponse(val page: Int,
                             @JsonProperty("total_results") val totalResults: Long,
                             @JsonProperty("total_pages") val totalPages: Int,
                             val results: List<Movie>)