package com.viona.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.viona.moviecatalogue.data.source.local.TVShowLocalDataSource
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.data.source.remote.TVShowRemoteDataSource
import com.viona.moviecatalogue.utils.AppExecutors
import com.viona.moviecatalogue.utils.DataDummy
import com.viona.moviecatalogue.utils.LiveDataTestUtil
import com.viona.moviecatalogue.utils.PagedListUtil
import com.viona.moviecatalogue.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

@Suppress("UNCHECKED_CAST")
class TVShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(TVShowRemoteDataSource::class.java)
    private val local = Mockito.mock(TVShowLocalDataSource::class.java)
    private val appExecutor = Mockito.mock(AppExecutors::class.java)
    private val dataDummy = DataDummy()

    private val repository = TVShowRepository(remote, local, appExecutor)

    private val sampleTVShowsResponse = dataDummy.getTVShow()
    private val sampleTVShowResponse = dataDummy.getDetailTVShow()
    private val sampleTVShowId = sampleTVShowResponse.id!!
    private val sampleTVShowEntity = TVShowEntity.fromTVShowResponse(sampleTVShowResponse)


    @Test
    fun getTVShow() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(local.getTVShows()).thenReturn(dataSourceFactory)
        repository.getTVShow()

        val tvShowsEntity = TVShowEntity.fromTVShowsResponse(dataDummy.getTVShow())!!
        val tvShowsPaged = PagedListUtil.mockPagedList(tvShowsEntity)
        val tvShowsResource = Resource.success(tvShowsPaged)

        Mockito.verify(local).getTVShows()
        assertNotNull(tvShowsResource.data)
        assertEquals(sampleTVShowsResponse.results?.size, tvShowsResource.data?.size)
    }

    @Test
    fun getDetailTVShow() {
        val dummyTVShow = MutableLiveData<TVShowEntity>()
        val tvShowEntity = TVShowEntity.fromTVShowResponse(sampleTVShowResponse)
        dummyTVShow.value = tvShowEntity
        Mockito.`when`(local.getTVShow(sampleTVShowId)).thenReturn(dummyTVShow)

        val tvShowLive = LiveDataTestUtil.getValue(repository.getDetailTVShow(sampleTVShowId))
        Mockito.verify(local).getTVShow(sampleTVShowId)
        assertNotNull(tvShowLive)
        assertNotNull(tvShowLive.data)
        assertEquals(tvShowEntity, tvShowLive.data)
    }

    @Test
    fun getFavoriteTVShow() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(local.getFavoriteTVShows()).thenReturn(dataSourceFactory)
        repository.getFavoriteTVShow()

        val tvShowsEntity = MovieEntity.fromMoviesResponse(dataDummy.getMovie())!!
        val tvShowsPaged = PagedListUtil.mockPagedList(tvShowsEntity)
        val tvShowsResource = Resource.success(tvShowsPaged)

        Mockito.verify(local).getFavoriteTVShows()
        assertNotNull(tvShowsResource)
        assertEquals(sampleTVShowsResponse.results?.size, tvShowsResource.data?.size)
    }

    @Test
    fun setFavoriteTVShow() {
        local.setTVShowFavorite(sampleTVShowEntity, true)

        Mockito.verify(local).setTVShowFavorite(sampleTVShowEntity, true)
        Mockito.verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteTVShowCount() {
        val randomNumber = MutableLiveData<Int>()
        randomNumber.value = (0 until 100).random()
        Mockito.`when`(local.getFavoriteCounts()).thenReturn(randomNumber)

        val countLive = LiveDataTestUtil.getValue(repository.getFavoriteTVShowCount())
        Mockito.verify(local).getFavoriteCounts()
        assertEquals(randomNumber.value, countLive)
    }
}