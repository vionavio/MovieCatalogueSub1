package com.viona.moviecatalogue.data.source.local

import com.google.gson.Gson
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import java.io.InputStreamReader

class LocalDataSource {
    fun getMovies(): MoviesResponse {
        return Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("movies.json")),
            MoviesResponse::class.java
        )
    }

    fun getMovieDetail(): DetailMovieResponse {
        return Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("movie.json")),
            DetailMovieResponse::class.java
        )
    }

    fun getTVShows(): TVShowsResponse {
        return Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("tv_shows.json")),
            TVShowsResponse::class.java
        )
    }

    fun getTVShowDetail(): TVShowDetailResponse {
        return Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("tv_show.json")),
            TVShowDetailResponse::class.java
        )

    }
}