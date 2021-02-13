package com.viona.moviecatalogue.ui.tv_show

import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.DataTVShow
import com.viona.moviecatalogue.models.TVShowEntity

class TVShowViewModel : ViewModel() {
    fun getTvShow(): List<TVShowEntity> = DataTVShow.generateDummyTVShow()
}