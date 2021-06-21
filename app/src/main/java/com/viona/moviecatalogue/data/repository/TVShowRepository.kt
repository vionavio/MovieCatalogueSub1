package com.viona.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.source.local.TVShowLocalDataSource
import com.viona.moviecatalogue.data.source.NetworkBoundResource
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.data.source.remote.ApiResponse
import com.viona.moviecatalogue.data.source.remote.TVShowRemoteDataSource
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import com.viona.moviecatalogue.utils.AppExecutors
import com.viona.moviecatalogue.vo.Resource

class TVShowRepository(
    private val remote: TVShowRemoteDataSource,
    private val local: TVShowLocalDataSource,
    private val appExecutors: AppExecutors
) : TVShowRepositoryInterface {

    override fun getTVShow(): LiveData<Resource<PagedList<TVShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TVShowEntity>, TVShowsResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TVShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(7)
                    .setPageSize(7)
                    .build()

                val localTVShows = local.getTVShows()
                return LivePagedListBuilder(localTVShows, config).build()
            }

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TVShowsResponse>> {
                return remote.getTVShow()
            }

            override fun saveCallResult(data: TVShowsResponse) {
                TVShowEntity.fromTVShowsResponse(data)?.let { local.insertTVShows(it) }
            }
        }.asLiveData()
    }

    override fun getDetailTVShow(id: Int): LiveData<Resource<TVShowEntity>> {
        return object : NetworkBoundResource<TVShowEntity, TVShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TVShowEntity> {
                return local.getTVShow(id)
            }

            override fun shouldFetch(data: TVShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<TVShowDetailResponse>> {
                return remote.getTVShowDetail(id)
            }

            override fun saveCallResult(data: TVShowDetailResponse) {
                val tvShow = TVShowEntity.fromTVShowResponse(data)
                local.insertTVShow(tvShow)
            }

        }.asLiveData()
    }

    override fun getFavoriteTVShow(): LiveData<PagedList<TVShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(local.getFavoriteTVShows(), config).build()
    }

    override fun setFavoriteTVShow(tvShow: TVShowEntity, state: Boolean) {
        return appExecutors.diskIO().execute { local.setTVShowFavorite(tvShow, state) }
    }

    override fun getFavoriteTVShowCount(): LiveData<Int> {
        return local.getFavoriteCounts()
    }
}