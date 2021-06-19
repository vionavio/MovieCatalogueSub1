package com.viona.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowResultsItem
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse

@Entity(tableName = "tv_show")
data class TVShowEntity(
    @PrimaryKey
    @NonNull
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val posterPath: String,
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
                tvShow?.overview ?: "",
                tvShow?.posterPath ?: "",
            )
        }
    }
}