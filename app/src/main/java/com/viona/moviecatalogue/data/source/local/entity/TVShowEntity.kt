package com.viona.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowResultsItem
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse

@Entity(tableName = "tv_show")
data class TVShowEntity(
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String?,

    @ColumnInfo(name = "originalName")
    val originalName: String,

    @ColumnInfo(name = "firstAirDate")
    val firstAirDate: String,

    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String,

    var favorite: Boolean = false
) {
    companion object {
        fun fromTVShowsResponse(tvShows: TVShowsResponse): List<TVShowEntity>? {
            return tvShows.results?.map { fromTVShowItem(it) }
        }

        fun fromTVShowResponse(tvShow: TVShowDetailResponse?): TVShowEntity {
            return fromTVShowItem(TVShowResultsItem.fromTVShowDetailResponse(tvShow))
        }

        private fun fromTVShowItem(tvShow: TVShowResultsItem?): TVShowEntity {
            return TVShowEntity(
                tvShow?.id ?: 0,
                tvShow?.name ?: "",
                tvShow?.voteAverage ?: 0.0,
                tvShow?.voteCount ?: 0,
                tvShow?.popularity ?: 0.0,
                tvShow?.overview ?: "",
                tvShow?.posterPath ?: "",
                tvShow?.backdropPath ?: "",
                tvShow?.originalName ?: "",
                tvShow?.firstAirDate ?: "",
                tvShow?.originalLanguage ?: "",
                false
            )
        }
    }
}