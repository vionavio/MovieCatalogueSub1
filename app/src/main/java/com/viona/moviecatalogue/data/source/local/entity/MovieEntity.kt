package com.viona.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*
import com.viona.moviecatalogue.data.source.remote.response.GenresItem
import com.viona.moviecatalogue.data.source.remote.response.SpokenLanguagesItem
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull

    @ColumnInfo(name = "movieId")
    val movieId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "tagline")
    val tagline: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "posterPath")
    val posterPath: String,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double,

    @ColumnInfo(name = "voteCount")
    val voteCount: Int,


    val status: String,

    val releaseDate: String,

    val spokenLanguages: List<SpokenLanguagesItem?>?,

    val genres: List<GenresItem?>?,

    val budget: Int,


    val homepage: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)

