package com.viona.moviecatalogue.ui.movie

import com.viona.moviecatalogue.models.MovieEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {
        val movieEntities = viewModel.getMovie()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities.size)
    }

    @Test
    fun emptyMovie() {
        val movieEntities = listOf<MovieEntity>()
        assertFalse(movieEntities.isNotEmpty())
        assertNotEquals(12, movieEntities.size)
    }
}