package com.viona.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.vo.Resource

interface MovieRepositoryInterface {

    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun getFavoriteMovieCount(): LiveData<Int>
}
