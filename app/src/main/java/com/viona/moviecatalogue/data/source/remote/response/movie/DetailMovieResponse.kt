package com.viona.moviecatalogue.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName
import com.viona.moviecatalogue.data.source.remote.response.GenresItem
import com.viona.moviecatalogue.data.source.remote.response.ProductionCompaniesItem
import com.viona.moviecatalogue.data.source.remote.response.ProductionCountriesItem
import com.viona.moviecatalogue.data.source.remote.response.SpokenLanguagesItem

data class DetailMovieResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("imdb_id")
    val imdbId: String? = null,

    val video: Boolean? = null,

    val title: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    val revenue: Int? = null,

    val genres: List<GenresItem?>? = null,

    val popularity: Double? = null,

    @field:SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem?>? = null,

    val id: Int? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null,

    val budget: Int? = null,

    val overview: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    val runtime: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem?>? = null,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("belongs_to_collection")
    val belongsToCollection: Any? = null,

    val tagline: String? = null,

    val adult: Boolean? = null,

    val homepage: String? = null,

    val status: String? = null
){
    companion object {
        fun fromMovieDetailResponse(movie: DetailMovieResponse?): DetailMovieResponse {
            return DetailMovieResponse(
                id = movie?.id ?: 0,
                title = movie?.title ?: "",
                tagline = movie?.tagline ?: "",
                voteAverage = movie?.voteAverage ?: 0.0,
                popularity = movie?.popularity ?: 0.0,
                voteCount = movie?.voteCount ?: 0,
                status = movie?.status ?: "",
                overview = movie?.overview ?: "",
                releaseDate = movie?.releaseDate ?: "",
                spokenLanguages = movie?.spokenLanguages,
                genres = movie?.genres,
                budget = movie?.budget ?: 0,
                homepage = movie?.homepage ?: "",
                posterPath = movie?.posterPath ?: "",
            )
        }
    }
}






