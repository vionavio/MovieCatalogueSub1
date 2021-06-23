package com.viona.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.repository.TVShowRepository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var favoriteViewModel: FavoriteViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var tvShowRepository: TVShowRepository

    @Mock
    private lateinit var observer: Observer<Int>

    @Before
    fun setUp() {
        favoriteViewModel = FavoriteViewModel(movieRepository, tvShowRepository)
    }

    @Test
    fun getFavoriteMovieCount() {
        val numberLive = MutableLiveData<Int>()
        val numberRandom = (0 until 100).random()
        numberLive.value = numberRandom
        Mockito.`when`(movieRepository.getFavoriteMovieCount()).thenReturn(numberLive)

        val count = favoriteViewModel.getFavoriteMovieCount().value
        verify(movieRepository).getFavoriteMovieCount()
        Mockito.verifyNoInteractions(tvShowRepository)
        assertNotNull(count)
        assertEquals(numberRandom, count)

        favoriteViewModel.getFavoriteMovieCount().observeForever(observer)
        verify(observer).onChanged(numberRandom)
    }

    @Test
    fun getFavoriteTVShowCount() {
        val numberLive = MutableLiveData<Int>()
        val numberRandom = (0 until 100).random()
        numberLive.value = numberRandom
        Mockito.`when`(tvShowRepository.getFavoriteTVShowCount()).thenReturn(numberLive)

        val count = favoriteViewModel.getFavoriteTVShowCount().value
        verify(tvShowRepository).getFavoriteTVShowCount()
        Mockito.verifyNoInteractions(movieRepository)
        assertNotNull(count)
        assertEquals(numberRandom, count)

        favoriteViewModel.getFavoriteTVShowCount().observeForever(observer)
        verify(observer).onChanged(numberRandom)
    }
}