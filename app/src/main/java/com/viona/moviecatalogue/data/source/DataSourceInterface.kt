package com.viona.moviecatalogue.data.source

import androidx.lifecycle.MutableLiveData
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse

interface DataSourceInterface {
    fun getMovies(): MutableLiveData<MoviesResponse?>
    fun getMovieDetail(id: Int): MutableLiveData<DetailMovieResponse?>
    fun getTVShows(): MutableLiveData<TVShowsResponse?>
    fun getTVShowDetail(id: Int): MutableLiveData<TVShowDetailResponse?>
}