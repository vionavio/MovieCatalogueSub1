package com.viona.moviecatalogue.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteMovieViewModel

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(repository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = pagedList
        val randomNumber = (0 until 100).random()
        `when`(dummyMovies.size).thenReturn(randomNumber)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(repository.getFavoriteMovie()).thenReturn(movies)
        val moviesEntity = viewModel.getFavoriteMovie().value

        verify(repository).getFavoriteMovie()
        assertNotNull(moviesEntity)
        assertEquals(randomNumber, moviesEntity?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}