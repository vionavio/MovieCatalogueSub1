package com.viona.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity

@Dao
interface TVShowDao {
    @Query("SELECT * FROM tv_show ORDER BY popularity DESC")
    fun getTVShow(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM tv_show WHERE favorite = 1")
    fun getFavoriteTVShow(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT COUNT(id) FROM tv_show WHERE favorite = 1")
    fun getFavoriteCount(): LiveData<Int>

    @Query("SELECT * FROM tv_show where id = :id")
    fun getDetailTVShow(id: Int): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(tv_show: TVShowEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movie: List<TVShowEntity>)

    @Update
    fun updateTVShow(tv_show: TVShowEntity)

    @Delete
    fun deleteTVShow(tv_show: TVShowEntity)
}