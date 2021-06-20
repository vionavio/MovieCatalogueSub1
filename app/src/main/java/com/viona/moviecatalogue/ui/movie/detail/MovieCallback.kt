package com.viona.moviecatalogue.ui.movie.detail

import com.viona.moviecatalogue.data.source.local.entity.MovieEntity

interface MovieCallback {
    fun onShareClick(movie: MutableList<String?>)
}