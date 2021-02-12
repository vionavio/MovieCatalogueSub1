package com.viona.moviecatalogue.ui.movie

import com.viona.moviecatalogue.data.DataMovie

interface MovieCallback {
    fun onShareClick(movie: DataMovie)
}