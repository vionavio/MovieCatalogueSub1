package com.viona.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.data.source.local.room.AppDatabase

class MovieLocalDataSource(database: AppDatabase) {
    private var movieDao = database.movieDao()

    fun getMovie(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMovie()
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getFavoriteMovie()
    }

    fun getFavoriteCount(): LiveData<Int> {
        return movieDao.getFavoriteCount()
    }

    fun getDetailMovie(id: Int): LiveData<MovieEntity> {
        return movieDao.getDetailMovie(id)
    }

    fun insertMovie(movies: List<MovieEntity>) {
        return movieDao.insertAll(movies)
    }

    fun insertMovie(movieEntity: MovieEntity) {
        movieDao.insertMovie(movieEntity)
    }

    fun setMovieFavorite(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.favorite = newState
        movieDao.updateMovie(movieEntity)
    }
}