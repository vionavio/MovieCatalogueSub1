package com.viona.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData

import com.viona.moviecatalogue.data.source.remote.RemoteHelper.call
import com.viona.moviecatalogue.data.network.ApiService
import com.viona.moviecatalogue.data.source.MovieDataSourceInterface
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse

class MovieRemoteDataSource(private val apiService: ApiService) : MovieDataSourceInterface {
    override fun getMovies(): LiveData<ApiResponse<MoviesResponse>> {
        return call(apiService.getMovies())
    }

    override fun getMovieDetail(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        return call(apiService.getMovieDetail(id))
    }

}