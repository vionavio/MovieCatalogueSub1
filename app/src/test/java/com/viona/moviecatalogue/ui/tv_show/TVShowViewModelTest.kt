package com.viona.moviecatalogue.ui.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.repository.TVShowRepository
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
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
class TVShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TVShowViewModel

    @Mock
    private lateinit var repository: TVShowRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TVShowEntity>


    @Before
    fun setUp() {
        viewModel = TVShowViewModel(repository)
    }

    @Test
    fun getTVShows() {
        val dummyTVShow = Resource.success(pagedList)
        val randomNumber = (0 until 100).random()
        Mockito.`when`(dummyTVShow.data?.size).thenReturn(randomNumber)
        val tvShows = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        tvShows.value = dummyTVShow

        Mockito.`when`(repository.getTVShow()).thenReturn(tvShows)
        val tvShowsEntity = viewModel.getTVShows().value?.data

        Mockito.verify(repository).getTVShow()
        assertNotNull(tvShowsEntity)
        assertEquals(randomNumber, tvShowsEntity?.size)

        viewModel.getTVShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTVShow)
    }
}