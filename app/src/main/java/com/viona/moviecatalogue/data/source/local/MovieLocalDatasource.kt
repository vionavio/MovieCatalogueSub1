package com.viona.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.data.source.local.room.AppDatabase

class MovieLocalDatasource(database: AppDatabase) {
    private var movieDao = database.movieDao()

    fun getMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMovie()
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getFavoriteMovie()
    }

    fun getFavoriteCounts(): LiveData<Int> {
        return movieDao.getFavoriteCount()
    }

    fun getMovie(id: Int): LiveData<MovieEntity> {
        return movieDao.getDetailMovie(id)
    }

    fun insertMovies(movies: List<MovieEntity>) {
        return movieDao.insertAll(movies)
    }

    fun insertMovie(movieEntity: MovieEntity) {
        movieDao.insertMovie(movieEntity)
    }

    fun updateMovie(movieEntity: MovieEntity) {
        movieDao.updateMovie(movieEntity)
    }

    fun setMovieFavorite(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.favorite = newState
        movieDao.updateMovie(movieEntity)
    }
}