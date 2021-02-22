package com.viona.moviecatalogue.data.source.remote.response.tvShow

import com.google.gson.annotations.SerializedName
import com.viona.moviecatalogue.data.source.remote.response.GenresItem
import com.viona.moviecatalogue.data.source.remote.response.ProductionCompaniesItem
import com.viona.moviecatalogue.data.source.remote.response.ProductionCountriesItem
import com.viona.moviecatalogue.data.source.remote.response.SpokenLanguagesItem

data class TVShowDetailResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,
    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,
    val networks: List<NetworksItem?>? = null,
    val type: String? = null,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val genres: List<GenresItem?>? = null,
    val popularity: Double? = null,
    @field:SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem?>? = null,
    val id: Int? = null,
    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,
    @field:SerializedName("vote_count")
    val voteCount: Int? = null,
    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,
    val overview: String? = null,
    val seasons: List<SeasonsItem?>? = null,
    val languages: List<String?>? = null,
    @field:SerializedName("created_by")
    val createdBy: List<CreatedByItem?>? = null,
    @field:SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,
    @field:SerializedName("poster_path")
    val posterPath: String? = null,
    @field:SerializedName("origin_country")
    val originCountry: List<String?>? = null,
    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem?>? = null,
    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,
    @field:SerializedName("original_name")
    val originalName: String? = null,
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,
    val name: String? = null,
    val tagline: String? = null,
    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int?>? = null,

    @field:SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any? = null,

    @field:SerializedName("in_production")
    val inProduction: Boolean? = null,

    @field:SerializedName("last_air_date")
    val lastAirDate: String? = null,

    @field:SerializedName("homepage")
    val homepage: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class SeasonsItem(

    @field:SerializedName("air_date")
    val airDate: String? = null,
    val overview: String? = null,
    @field:SerializedName("episode_count")
    val episodeCount: Int? = null,
    val name: String? = null,
    @field:SerializedName("season_number")
    val seasonNumber: Int? = null,
    val id: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null
)

data class LastEpisodeToAir(

    @field:SerializedName("production_code")
    val productionCode: String? = null,
    @field:SerializedName("air_date")
    val airDate: String? = null,

    val overview: String? = null,
    @field:SerializedName("episode_number")
    val episodeNumber: Int? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    val name: String? = null,

    @field:SerializedName("season_number")
    val seasonNumber: Int? = null,

    val id: Int? = null,
    @field:SerializedName("still_path")
    val stillPath: String? = null,
    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)

data class NetworksItem(

    @field:SerializedName("logo_path")
    val logoPath: String? = null,

    val name: String? = null,

    val id: Int? = null,

    @field:SerializedName("origin_country")
    val originCountry: String? = null
)

data class CreatedByItem(

    val gender: Int? = null,

    @field:SerializedName("credit_id")
    val creditId: String? = null,

    val name: String? = null,

    @field:SerializedName("profile_path")
    val profilePath: String? = null,

    val id: Int? = null
)

