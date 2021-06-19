package com.viona.moviecatalogue.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse

class DetailMovieViewModel(private val dataRepository: DataRepository) : ViewModel() {

    lateinit var movie: LiveData<DetailMovieResponse?>
    private var id: Int = 0

    fun setMovieId(id: Int) {
        this.id = id
    }

    fun getMovieDetail() {
        movie = dataRepository.getMovieDetail(id)
    }
}