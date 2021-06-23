package com.viona.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "posterPath")
    val posterPath: String?,

    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int,

    @ColumnInfo(name = "originalTitle")
    val originalTitle: String,

    @ColumnInfo(name = "originalLanguage")
    val originalLanguage: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) {
    companion object {
        fun fromMoviesResponse(movies: MoviesResponse): List<MovieEntity>? {
            return movies.results?.map { fromMovieItem(it) }
        }

        fun fromMovieResponse(movie: MovieResultsItem?): MovieEntity {
            return fromMovieItem(MovieResultsItem.fromMovieDetailResponse(movie))
        }

        private fun fromMovieItem(movie: MovieResultsItem?): MovieEntity {
            return MovieEntity(
                movie?.id ?: 0,
                movie?.title ?: "",
                movie?.overview ?: "",
                movie?.posterPath ?: "",
                movie?.backdropPath ?: "",
                movie?.popularity ?: 0.0,
                movie?.releaseDate ?: "",
                movie?.voteAverage ?: 0.0,
                movie?.voteCount ?: 0,
                movie?.originalTitle ?: "",
                movie?.originalLanguage ?: "",
                false
            )
        }
    }
}

