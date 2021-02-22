package com.viona.moviecatalogue.ui.movie.detail

import com.viona.moviecatalogue.data.source.remote.response.movie.MovieResultsItem

interface MovieCallback {
    fun onShareClick(movie: MovieResultsItem)
}