package com.viona.moviecatalogue.ui.tv_show.detail

import com.viona.moviecatalogue.models.TVShowEntity

interface TVShowCallback {
    fun onShareClick(tvShow: TVShowEntity)
}