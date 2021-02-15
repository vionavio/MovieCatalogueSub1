package com.viona.moviecatalogue.ui.movie.detail

import com.viona.moviecatalogue.data.DataMovie
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dataMovie = DataMovie.generateDummyMovie()[0]
    private val movieId = dataMovie.movieId

    @Before
    fun setUp(){
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun setSelectedMovie() {
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dataMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dataMovie.movieId, movieEntity.movieId)
        assertEquals(dataMovie.title, movieEntity.title)
        assertEquals(dataMovie.year, movieEntity.year)
        assertEquals(dataMovie.description, movieEntity.description)
        assertEquals(dataMovie.director, movieEntity.director)
        assertEquals(dataMovie.writers, movieEntity.writers)
        assertEquals(dataMovie.stars, movieEntity.stars)
        assertEquals(dataMovie.rating, movieEntity.rating, 0.0001)
        assertEquals(dataMovie.imagePath, movieEntity.imagePath)
        assertEquals(dataMovie.tomatometer, movieEntity.tomatometer)
        assertEquals(dataMovie.people_rate, movieEntity.people_rate)
        assertEquals(dataMovie.duration, movieEntity.duration)
        assertEquals(dataMovie.price, movieEntity.price)
    }
}