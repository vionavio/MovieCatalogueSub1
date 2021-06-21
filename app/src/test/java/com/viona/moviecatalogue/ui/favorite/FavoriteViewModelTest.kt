package com.viona.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.repository.TVShowRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: FavoriteViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvShowRepository: TVShowRepository

    @Mock
    private lateinit var observer: Observer<Int>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository, tvShowRepository)
    }

    @Test
    fun getFavoriteMoviesCount() {
        val numberLive = MutableLiveData<Int>()
        val numberRandom = (0 until 100).random()
        numberLive.value = numberRandom
        `when`(movieRepository.getFavoriteMovieCount()).thenReturn(numberLive)

        val count = viewModel.getFavoriteMovieCount().value
        verify(movieRepository).getFavoriteMovieCount()
        verifyNoInteractions(tvShowRepository)
        assertNotNull(count)
        assertEquals(numberRandom, count)

        viewModel.getFavoriteMovieCount().observeForever(observer)
        verify(observer).onChanged(numberRandom)
    }

    @Test
    fun getFavoriteTVShowsCount() {
        val numberLive = MutableLiveData<Int>()
        val numberRandom = (0 until 100).random()
        numberLive.value = numberRandom
        `when`(tvShowRepository.getFavoriteTVShowCount()).thenReturn(numberLive)

        val count = viewModel.getFavoriteTVShowCount().value
        verify(tvShowRepository).getFavoriteTVShowCount()
        verifyNoInteractions(movieRepository)
        assertNotNull(count)
        assertEquals(numberRandom, count)

        viewModel.getFavoriteTVShowCount().observeForever(observer)
        verify(observer).onChanged(numberRandom)
    }
}