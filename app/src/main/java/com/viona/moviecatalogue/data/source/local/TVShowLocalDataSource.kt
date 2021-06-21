package com.viona.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.data.source.local.room.AppDatabase

class TVShowLocalDataSource(database: AppDatabase) {
    private var tvShowDao = database.tvShowDao()

    fun getTVShows(): DataSource.Factory<Int, TVShowEntity> {
        return tvShowDao.getTVShow()
    }

    fun getFavoriteTVShows(): DataSource.Factory<Int, TVShowEntity> {
        return tvShowDao.getFavoriteTVShow()
    }

    fun getFavoriteCounts(): LiveData<Int> {
        return tvShowDao.getFavoriteCount()
    }

    fun getTVShow(id: Int): LiveData<TVShowEntity> {
        return tvShowDao.getDetailTVShow(id)
    }

    fun insertTVShows(tvShows: List<TVShowEntity>) {
        return tvShowDao.insertAll(tvShows)
    }

    fun insertTVShow(tvShowEntity: TVShowEntity) {
        tvShowDao.insertTVShow(tvShowEntity)
    }

    fun setTVShowFavorite(tvShowEntity: TVShowEntity, newState: Boolean) {
        tvShowEntity.favorite = newState
        tvShowDao.updateTVShow(tvShowEntity)
    }
}