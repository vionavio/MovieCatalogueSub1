package com.viona.moviecatalogue.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse

class MovieViewModel(private val repository: DataRepository) : ViewModel() {
    var movies = MutableLiveData<MoviesResponse?>()

    fun getMovie() {
        movies = repository.getMovies()
    }
}