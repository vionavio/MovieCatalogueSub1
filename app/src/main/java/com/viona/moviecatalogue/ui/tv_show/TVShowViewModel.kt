package com.viona.moviecatalogue.ui.tv_show

import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.TVShowRepository

class TVShowViewModel(private val dataRepository: TVShowRepository) : ViewModel() {

    fun getTVShow() = dataRepository.getTVShow()
}