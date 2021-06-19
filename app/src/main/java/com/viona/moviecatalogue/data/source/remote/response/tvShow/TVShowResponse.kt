package com.viona.moviecatalogue.data.source.remote.response.tvShow

import com.google.gson.annotations.SerializedName

data class TVShowsResponse(

    val page: Int? = null,
    @field:SerializedName("total_pages")
    val totalPages: Int? = null,
    val results: List<TVShowResultsItem?>? = null,
    @field:SerializedName("total_results")
    val totalResults: Int? = null
)

data class TVShowResultsItem(

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,
    val overview: String? = null,
    @field:SerializedName("original_language")
    val originalLanguage: String? = null,
    @field:SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    @field:SerializedName("origin_country")
    val originCountry: List<String?>? = null,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @field:SerializedName("original_name")
    val originalName: String? = null,
    val popularity: Double? = null,
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,
    val name: String? = null,
    val id: Int? = null,
    @field:SerializedName("vote_count")
    val voteCount: Int? = null
){
    companion object {
        fun fromTVShowDetailResponse(tvShow: TVShowDetailResponse?): TVShowResultsItem {
            return TVShowResultsItem(
                id = tvShow?.id ?: 0,
                name = tvShow?.name ?: "",
                overview = tvShow?.overview ?: "",
                popularity = tvShow?.popularity ?: 0.0,
                posterPath = tvShow?.posterPath ?: "",
                firstAirDate = tvShow?.firstAirDate ?: "",
                voteAverage = tvShow?.voteAverage ?: 0.0,
                voteCount = tvShow?.voteCount ?: 0,
            )
        }
    }
}