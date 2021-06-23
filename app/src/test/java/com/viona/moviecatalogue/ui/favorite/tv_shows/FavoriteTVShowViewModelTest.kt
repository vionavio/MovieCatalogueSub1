package com.viona.moviecatalogue.ui.favorite.tv_shows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.repository.TVShowRepository
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTVShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var favoriteTVShowViewModel: FavoriteTVShowViewModel

    @Mock
    private lateinit var repository: TVShowRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TVShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        favoriteTVShowViewModel = FavoriteTVShowViewModel(repository)
    }

    @Test
    fun favoriteTVShow() {
        val dummyTVShows = pagedList
        val randomNumber = (0 until 100).random()
        Mockito.`when`(dummyTVShows.size).thenReturn(randomNumber)

        val movies = MutableLiveData<PagedList<TVShowEntity>>()
        movies.value = dummyTVShows

        Mockito.`when`(repository.getFavoriteTVShow()).thenReturn(movies)
        val moviesEntity = favoriteTVShowViewModel.favoriteTVShow().value

        Mockito.verify(repository).getFavoriteTVShow()
        assertNotNull(moviesEntity)
        assertEquals(randomNumber, moviesEntity?.size)

        favoriteTVShowViewModel.favoriteTVShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTVShows)
    }
}