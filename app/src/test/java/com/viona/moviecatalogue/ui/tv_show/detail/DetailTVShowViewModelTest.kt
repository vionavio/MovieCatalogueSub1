package com.viona.moviecatalogue.ui.tv_show.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.viona.moviecatalogue.data.repository.TVShowRepository
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.utils.DataDummy
import com.viona.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTVShowViewModelTest {

    private val dataDummy = DataDummy()
    private lateinit var viewModel: DetailTVShowViewModel
    private val dummyTVShow = dataDummy.getDetailTVShow()
    private val dummyTVShowId = dummyTVShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TVShowRepository

    @Mock
    private lateinit var observer: Observer<Resource<TVShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTVShowViewModel(repository)
        if (dummyTVShowId != null) {
            viewModel.setTVShowId(dummyTVShowId)
        }
    }

    @Test
    fun getTvShow() {
        val tvShowEntity = TVShowEntity.fromTVShowResponse(dummyTVShow)
        val tvShowResource = Resource.success(tvShowEntity)
        val tvShowLive = MutableLiveData<Resource<TVShowEntity>>()
        tvShowLive.value = tvShowResource

        Mockito.`when`(dummyTVShowId?.let { repository.getDetailTVShow(it) }).thenReturn(tvShowLive)

        viewModel.tvShow.observeForever(observer)
        Mockito.verify(observer).onChanged(tvShowResource)

        val movie = viewModel.tvShow.value?.data

        assertNotNull(movie)
        assertEquals(dummyTVShow.id, movie?.id)
        assertEquals(dummyTVShow.name, movie?.name)
        assertEquals(dummyTVShow.originalLanguage, movie?.originalLanguage)
        assertEquals(dummyTVShow.overview, movie?.overview)
        assertEquals(dummyTVShow.posterPath, movie?.posterPath)
        assertEquals(dummyTVShow.backdropPath, movie?.backdrop_path)
        assertEquals(dummyTVShow.firstAirDate, movie?.firstAirDate)
        assertEquals(dummyTVShow.voteCount, movie?.voteCount)

        assertEquals(dummyTVShow.popularity as Double, movie?.popularity as Double, 0.0001)
        assertEquals(dummyTVShow.voteAverage as Double, movie.voteAverage, 0.0001)
    }

}