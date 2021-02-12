package com.viona.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.models.MovieEntity
import com.viona.moviecatalogue.data.DataMovie

class MovieViewModel : ViewModel() {
    fun getMovie(): List<MovieEntity> = DataMovie.generateDummyMovie()
}