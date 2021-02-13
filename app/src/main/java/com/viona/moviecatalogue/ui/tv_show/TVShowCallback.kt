package com.viona.moviecatalogue.ui.tv_show

import com.viona.moviecatalogue.data.DataTVShow

interface TVShowCallback {
    fun onShareClick(tvShow: DataTVShow)
}