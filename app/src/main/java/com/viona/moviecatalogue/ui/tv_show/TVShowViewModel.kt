package com.viona.moviecatalogue.ui.tv_show

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse

class TVShowViewModel(private val dataRepository: DataRepository) : ViewModel() {
    var tvShows = MutableLiveData<TVShowsResponse?>()

    fun getTVShows() {
        tvShows = dataRepository.getTVShows()
    }
}