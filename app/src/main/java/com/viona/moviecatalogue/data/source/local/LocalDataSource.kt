package com.viona.moviecatalogue.data.source.local

import com.google.gson.Gson
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import java.io.InputStreamReader

class LocalDataSource {
    fun getMovies(): MoviesResponse {
        return Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_movies.json")),
            MoviesResponse::class.java
        )
    }

    fun getMovieDetail(): MovieDetailResponse {
        return Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_movie.json")),
            MovieDetailResponse::class.java
        )
    }

//    fun getRandomMovieDetail(): MovieResultsItem {
//        val movies = Gson().fromJson(
//            InputStreamReader(javaClass.getResourceAsStream("get_movies.json")),
//            MoviesResponse::class.java
//        )
//
//        return movies.results.random()
//    }

    fun getTVShows(): TVShowsResponse {
        return Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_tv_shows.json")),
            TVShowsResponse::class.java
        )
    }

    fun getTVShowDetail(): TVShowDetailResponse {
        return Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_tv_show.json")),
            TVShowDetailResponse::class.java
        )

    }
}