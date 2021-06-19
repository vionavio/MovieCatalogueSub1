package com.viona.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import com.viona.moviecatalogue.data.network.ApiService
import com.viona.moviecatalogue.data.source.TVShowDataSourceInterface
import com.viona.moviecatalogue.data.source.remote.RemoteHelper.call
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse

class TVShowRemoteDataSource(private val apiService: ApiService) : TVShowDataSourceInterface {
    override fun getTVShows(): LiveData<ApiResponse<TVShowsResponse>> {
        return call(apiService.getTVShows())
    }

    override fun getTVShowDetail(id: Int): LiveData<ApiResponse<TVShowDetailResponse>> {
        return  call(apiService.getTVShowDetail(id))
    }
}