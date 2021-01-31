package com.viona.moviecatalogue.ui.movie.detail

import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.MovieEntity
import com.viona.moviecatalogue.utils.DataDummyMovie

class DetailMovieViewModel : ViewModel() {
    private lateinit var movieId: String
    private lateinit var movie: MovieEntity

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): MovieEntity {
        val movieEntities = DataDummyMovie.generateDummyMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun get
}