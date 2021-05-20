package com.viona.moviecatalogue.ui.tv_show.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
class DetailTVShowViewModelTest {

    private lateinit var viewModel: DetailTVShowViewModel
    private lateinit var dataTVShow: TVShowDetailResponse
    private var tvShowId: Int = 0

    @Mock
    private lateinit var repository: DataRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<TVShowDetailResponse?>


    @Before
    fun setUp() {
        viewModel = DetailTVShowViewModel(repository)
    }

    @Test
    fun setSelectedTVShow() {
    }

    @Test
    fun getTVShow() {
        dataTVShow = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("tv_show.json")),
            TVShowDetailResponse::class.java
        )
        val tvShowLive = MutableLiveData<TVShowDetailResponse>()
        tvShowLive.value = dataTVShow

        dataTVShow.id?.let { tvShowId = it }
        Mockito.`when`(repository.getTVShowDetail(tvShowId))
            .thenReturn(MutableLiveData(dataTVShow))

        viewModel.setTVShowId(tvShowId)
        viewModel.getTVShowDetail()
        val tvShow = viewModel.tvShow.value

        assertNotNull(tvShow)

        assertEquals(dataTVShow.id, tvShow?.id)
        assertEquals(dataTVShow.name, tvShow?.name)
        assertEquals(dataTVShow.originalName, tvShow?.originalName)
        assertEquals(dataTVShow.voteAverage as Double, tvShow?.voteAverage as Double, 0.0001)
        assertEquals(dataTVShow.voteCount, tvShow.voteCount)
        assertEquals(dataTVShow.numberOfEpisodes, tvShow.numberOfEpisodes)
        assertEquals(dataTVShow.numberOfSeasons, tvShow.numberOfSeasons)
        assertEquals(dataTVShow.spokenLanguages, tvShow.spokenLanguages)
        assertEquals(dataTVShow.genres, tvShow.genres)
        assertEquals(dataTVShow.overview, tvShow.overview)
        assertEquals(dataTVShow.status, tvShow.status)
        assertEquals(dataTVShow.posterPath, tvShow.posterPath)
        assertEquals(dataTVShow.firstAirDate, tvShow.firstAirDate)
        assertEquals(dataTVShow.popularity as Double, tvShow.popularity as Double, 0.0001)

        viewModel.tvShow.observeForever(observer)
        verify(observer).onChanged(dataTVShow)
    }
}