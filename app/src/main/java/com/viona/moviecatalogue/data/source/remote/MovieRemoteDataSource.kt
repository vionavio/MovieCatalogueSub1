package com.viona.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import com.viona.moviecatalogue.data.network.ApiService
import com.viona.moviecatalogue.data.source.MovieDataSource
import com.viona.moviecatalogue.data.source.remote.RemoteHelper.call
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse

class MovieRemoteDataSource(private val apiService: ApiService) : MovieDataSource {
    override fun getMovie(): LiveData<ApiResponse<MoviesResponse>> {
        return call(apiService.getMovies())
    }

    override fun getMovieDetail(id: Int): LiveData<ApiResponse<MovieResultsItem>> {
        return call(apiService.getMovieDetail(id))
    }

}