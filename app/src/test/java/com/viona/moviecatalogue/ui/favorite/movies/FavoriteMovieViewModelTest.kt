package com.viona.moviecatalogue.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        favoriteMovieViewModel = FavoriteMovieViewModel(repository)
    }

    @Test
    fun favoriteMovie() {
        val dummyMovies = pagedList
        val randomNumber = (0 until 100).random()
        Mockito.`when`(dummyMovies.size).thenReturn(randomNumber)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(repository.getFavoriteMovie()).thenReturn(movies)
        val movieEntity = favoriteMovieViewModel.favoriteMovie().value

        Mockito.verify(repository).getFavoriteMovie()
        assertNotNull(movieEntity)
        assertEquals(randomNumber, movieEntity?.size)

        favoriteMovieViewModel.favoriteMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}