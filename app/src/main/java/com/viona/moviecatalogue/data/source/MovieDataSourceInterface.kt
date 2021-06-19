package com.viona.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.viona.moviecatalogue.data.source.remote.ApiResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse

interface MovieDataSourceInterface {
    fun getMovies(): LiveData<ApiResponse<MoviesResponse>>
    fun getMovieDetail(id: Int): LiveData<ApiResponse<DetailMovieResponse>>
}