package com.viona.moviecatalogue.ui.movie.detail

import com.viona.moviecatalogue.models.MovieEntity

interface MovieCallback {
    fun onShareClick(movie: MovieEntity)
}