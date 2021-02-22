package com.viona.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    val results: List<MovieResultsItem?>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)