package com.viona.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.MovieRepository

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getMovie()  = repository.getMovie()

}