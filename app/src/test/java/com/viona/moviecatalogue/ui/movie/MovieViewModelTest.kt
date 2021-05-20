package com.viona.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.models.MovieEntity
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.InputStreamReader

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var repository: DataRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<MoviesResponse?>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovie() {
        val sampleMovies = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("movies.json")),
            MoviesResponse::class.java
        )
        val movies = MutableLiveData<MoviesResponse>()
        movies.value = sampleMovies

        Mockito.`when`(repository.getMovies()).thenReturn(MutableLiveData(sampleMovies))
        viewModel.getMovie()

        TestCase.assertNotNull(sampleMovies)
        TestCase.assertNotNull(viewModel.movies)
        assertEquals(sampleMovies, viewModel.movies.value)
        assertEquals(sampleMovies.results?.size, viewModel.movies.value?.results?.size)

        viewModel.movies.observeForever(observer)
        verify(observer).onChanged(sampleMovies)
    }

    @Test
    fun emptyMovie() {
        val movieEntities = listOf<MovieEntity>()
        assertFalse(movieEntities.isNotEmpty())
        assertNotEquals(12, movieEntities.size)
    }
}