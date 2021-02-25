package com.viona.moviecatalogue.ui.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import com.viona.moviecatalogue.models.TVShowEntity
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
class TVShowViewModelTest {
    private lateinit var viewModel: TVShowViewModel

    @Mock
    private lateinit var repository: DataRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<TVShowsResponse?>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(repository)
    }

    @Test
    fun getTVShow() {
        /*val tvShowEntities = viewModel.getTVShows()
        assertNotNull(tvShowEntities)
        assertEquals(12, tvShowEntities.size)*/
        val sampleTVShows = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_tv_shows.json")),
            TVShowsResponse::class.java
        )
        val tvShows = MutableLiveData<TVShowsResponse>()
        tvShows.value = sampleTVShows

        Mockito.`when`(repository.getTVShows()).thenReturn(MutableLiveData(sampleTVShows))
        viewModel.getTVShows()

        TestCase.assertNotNull(sampleTVShows)
        TestCase.assertNotNull(viewModel.tvShows)
        assertEquals(sampleTVShows, viewModel.tvShows.value)
        assertEquals(sampleTVShows.results?.size, viewModel.tvShows.value?.results?.size)

        viewModel.tvShows.observeForever(observer)
        verify(observer).onChanged(sampleTVShows)
    }

    @Test
    fun emptyTVShow() {
        val tvShowEntities = listOf<TVShowEntity>()
        assertFalse(tvShowEntities.isNotEmpty())
        assertNotEquals(12, tvShowEntities.size)
    }
}