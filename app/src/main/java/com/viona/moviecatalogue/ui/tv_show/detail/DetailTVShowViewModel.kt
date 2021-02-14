package com.viona.moviecatalogue.ui.tv_show.detail

import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.DataTVShow
import com.viona.moviecatalogue.models.TVShowEntity

class DetailTVShowViewModel : ViewModel() {
    private lateinit var tvShowId: String
    private lateinit var tvShow: TVShowEntity

    fun setSelectedTVShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTVShow(): TVShowEntity {
        val tvShowEntities = DataTVShow.generateDummyTVShow()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.tvShowId == tvShowId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}