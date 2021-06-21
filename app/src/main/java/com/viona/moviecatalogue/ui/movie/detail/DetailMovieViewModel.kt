package com.viona.moviecatalogue.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.vo.Resource

class DetailMovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private var id = MutableLiveData<Int>()

    fun setMovieId(id: Int) {
        this.id.value = id
    }

    var movie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(id) { mId ->
            repository.getDetailMovie(mId)
        }

    fun setFavorite() {
        val movieResource = movie.value

        if (movieResource != null) {
            val movieData = movieResource.data

            movieData?.let {
                repository.setFavoriteMovie(it, !it.favorite)
            }
        }
    }
}