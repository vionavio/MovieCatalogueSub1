package com.viona.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.MovieEntity
import com.viona.moviecatalogue.utils.DataDummyMovie

class MovieViewModel : ViewModel() {
    fun getMovie(): List<MovieEntity> = DataDummyMovie.generateDummyMovie()
}