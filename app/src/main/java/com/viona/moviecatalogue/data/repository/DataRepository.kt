package com.viona.moviecatalogue.data.repository

import androidx.lifecycle.MutableLiveData
import com.viona.moviecatalogue.data.source.remote.RemoteDataSource
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse

class DataRepository(private val remoteRemoteDataSource: RemoteDataSource) {
    fun getMovies(): MutableLiveData<MoviesResponse?> {
        return remoteRemoteDataSource.getMovies()
    }

    fun getMovieDetail(id: Int): MutableLiveData<MovieDetailResponse?> {
        return remoteRemoteDataSource.getMovieDetail(id)
    }

    fun getTVShows(): MutableLiveData<TVShowsResponse?> {
        return remoteRemoteDataSource.getTVShows()
    }

    fun getTVShowDetail(id: Int): MutableLiveData<TVShowDetailResponse?> {
        return remoteRemoteDataSource.getTVShowDetail(id)
    }
}