package com.viona.moviecatalogue.ui.tv_show.detail

import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowResultsItem

interface TVShowCallback {
    fun onShareClick(tvShow: TVShowResultsItem)
}