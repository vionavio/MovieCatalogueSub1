package com.viona.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.viona.moviecatalogue.data.source.remote.ApiResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse

interface TVShowDataSourceInterface {

    fun getTVShows(): LiveData<ApiResponse<TVShowsResponse>>
    fun getTVShowDetail(id: Int): LiveData<ApiResponse<TVShowDetailResponse>>
}
