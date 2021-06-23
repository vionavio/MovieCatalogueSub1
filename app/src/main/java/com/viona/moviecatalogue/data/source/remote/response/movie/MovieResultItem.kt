package com.viona.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResultsItem(

    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    val video: Boolean? = null,

    val title: String? = null,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    val id: Int? = null,

    val adult: Boolean? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
) {
    companion object {
        fun fromMovieDetailResponse(movie: MovieResultsItem?): MovieResultsItem {
            return MovieResultsItem(
                id = movie?.id ?: 0,
                title = movie?.title ?: "",
                overview = movie?.overview ?: "",
                posterPath = movie?.posterPath ?: "",
                backdropPath = movie?.backdropPath ?: "",
                popularity = movie?.popularity ?: 0.0,
                voteAverage = movie?.voteAverage ?:0.0,
                voteCount = movie?.voteCount ?: 0,
                originalTitle = movie?.originalTitle ?: "",
                originalLanguage = movie?.originalLanguage ?: "",
            )
        }
    }
}

