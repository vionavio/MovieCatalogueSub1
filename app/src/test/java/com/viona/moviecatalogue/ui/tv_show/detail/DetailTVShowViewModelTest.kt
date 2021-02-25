package com.viona.moviecatalogue.ui.tv_show.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.InputStreamReader

@RunWith(MockitoJUnitRunner::class)
class DetailTVShowViewModelTest {

    private lateinit var viewModel: DetailTVShowViewModel
    private lateinit var dataTVShow : TVShowDetailResponse
    private var tvShowId : Int = 0

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
        /*viewModel.setSelectedTVShow(dataTVShow.tvShowId)
        val tvShowEntity = viewModel.getTVShow()
        assertNotNull(tvShowEntity)
        assertEquals(dataTVShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dataTVShow.title, tvShowEntity.title)
        assertEquals(dataTVShow.year, tvShowEntity.year)
        assertEquals(dataTVShow.star, tvShowEntity.star)
        assertEquals(dataTVShow.description, tvShowEntity.description)
        assertEquals(dataTVShow.type, tvShowEntity.type)
        assertEquals(dataTVShow.episode, tvShowEntity.episode)
        assertEquals(dataTVShow.rating, tvShowEntity.rating, 0.0001)
        assertEquals(dataTVShow.price, tvShowEntity.price)
        assertEquals(dataTVShow.imagePath, tvShowEntity.imagePath)*/

        dataTVShow = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_tv_show.json")),
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
        assertEquals(dataTVShow.overview, tvShow?.overview)
        assertEquals(dataTVShow.posterPath, tvShow?.posterPath)
        assertEquals(dataTVShow.firstAirDate, tvShow?.firstAirDate)
        assertEquals(dataTVShow.voteCount, tvShow?.voteCount)

        assertEquals(dataTVShow.popularity as Double, tvShow?.popularity as Double, 0.0001)
        assertEquals(dataTVShow.voteAverage as Double, tvShow.voteAverage as Double, 0.0001)

        viewModel.tvShow.observeForever(observer)
        verify(observer).onChanged(dataTVShow)
    }
}