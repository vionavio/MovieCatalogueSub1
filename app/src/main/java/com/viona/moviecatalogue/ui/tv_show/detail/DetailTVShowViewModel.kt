package com.viona.moviecatalogue.ui.tv_show.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse

class DetailTVShowViewModel(private val dataRepository: DataRepository) : ViewModel() {
    lateinit var tvShow: LiveData<TVShowDetailResponse?>
    private var id: Int = 0

    fun setTVShowId(id: Int) {
        this.id = id
    }

    fun getTVShowDetail() {
        tvShow = dataRepository.getTVShowDetail(id)
    }
}