package com.viona.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.source.local.MovieLocalDataSource
import com.viona.moviecatalogue.data.source.NetworkBoundResource
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.data.source.remote.ApiResponse
import com.viona.moviecatalogue.data.source.remote.MovieRemoteDataSource
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.utils.AppExecutors
import com.viona.moviecatalogue.vo.Resource

class MovieRepository(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource,
    private val appExecutors: AppExecutors
) : MovieRepositoryInterface {

    override fun getDetailMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                val localMovies = local.getDetailMovie()
                return LivePagedListBuilder(localMovies, config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> {
                return remote.getMovies()
            }

            override fun saveCallResult(data: MoviesResponse) {
                MovieEntity.fromMoviesResponse(data)?.let { local.insertDetailMovie(it) }
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return local.getDetailMovie(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> {
                return remote.getMovieDetail(id)
            }

            override fun saveCallResult(data: DetailMovieResponse) {
                val movie = MovieEntity.fromMovieResponse(data)
                local.insertDetailMovie(movie)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(local.getFavoriteMovie(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute { local.setMovieFavorite(movie, state) }
    }

    override fun getFavoriteMovieCount(): LiveData<Int> {
        return local.getFavoriteCount()
    }
}