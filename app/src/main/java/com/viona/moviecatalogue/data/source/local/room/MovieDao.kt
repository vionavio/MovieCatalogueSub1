package com.viona.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT COUNT(id) FROM movie WHERE favorite = 1")
    fun getFavoriteCount(): LiveData<Int>

    @Query("SELECT * FROM movie where id = :id")
    fun getDetailMovie(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Delete
    fun deleteMovie(movie: MovieEntity)
}