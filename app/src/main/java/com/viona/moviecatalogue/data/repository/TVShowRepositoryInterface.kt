package com.viona.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.vo.Resource

interface TVShowRepositoryInterface {

    fun getTVShow(): LiveData<Resource<PagedList<TVShowEntity>>>

    fun getDetailTVShow(id: Int): LiveData<Resource<TVShowEntity>>

    fun getFavoriteTVShow(): LiveData<PagedList<TVShowEntity>>

    fun setFavoriteTVShow(tvShow: TVShowEntity, state: Boolean)

    fun getFavoriteTVShowCount(): LiveData<Int>
}
