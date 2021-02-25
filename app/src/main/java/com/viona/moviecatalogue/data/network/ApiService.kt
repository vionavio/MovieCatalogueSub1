package com.viona.moviecatalogue.data.network

import com.viona.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getMovies(): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: Int): Call<MovieDetailResponse>

    @GET("tv/top_rated")
    fun getTVShows(): Call<TVShowsResponse>

    @GET("tv/{id}")
    fun getTVShowDetail(@Path("id") id: Int): Call<TVShowDetailResponse>

}