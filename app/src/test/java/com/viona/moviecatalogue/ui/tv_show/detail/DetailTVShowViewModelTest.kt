package com.viona.moviecatalogue.ui.tv_show.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.viona.moviecatalogue.data.repository.TVShowRepository
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.utils.DataDummy
import com.viona.moviecatalogue.vo.Resource
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

@RunWith(MockitoJUnitRunner::class)
class TVShowDetailViewModelTest {
    private val dataDummy = DataDummy()
    private lateinit var viewModel: DetailTVShowViewModel
    private val sampleTVShow = dataDummy.getDetailTVShow()
    private val sampleTVShowId = sampleTVShow.id!!

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TVShowRepository

    @Mock
    private lateinit var observer: Observer<Resource<TVShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTVShowViewModel(repository)
        viewModel.setTVShowId(sampleTVShowId)
    }

    @Test
    fun getMovie() {
        val tvShowEntity = TVShowEntity.fromTVShowResponse(sampleTVShow)
        val tvShowResource = Resource.success(tvShowEntity)
        val tvShowLive = MutableLiveData<Resource<TVShowEntity>>()
        tvShowLive.value = tvShowResource

        Mockito.`when`(repository.getDetailTVShow(sampleTVShowId)).thenReturn(tvShowLive)

        viewModel.tvShow.observeForever(observer)
        verify(observer).onChanged(tvShowResource)

        val movie = viewModel.tvShow.value?.data

        assertNotNull(movie)
        assertEquals(sampleTVShow.id, movie?.id)
        assertEquals(sampleTVShow.name, movie?.name)
        assertEquals(sampleTVShow.overview, movie?.overview)
        assertEquals(sampleTVShow.posterPath, movie?.posterPath)
        assertEquals(sampleTVShow.firstAirDate, movie?.firstAirDate)
        assertEquals(sampleTVShow.voteCount, movie?.voteCount)

        assertEquals(sampleTVShow.popularity as Double, movie?.popularity as Double, 0.0001)
        assertEquals(sampleTVShow.voteAverage as Double, movie.voteAverage, 0.0001)
    }
}