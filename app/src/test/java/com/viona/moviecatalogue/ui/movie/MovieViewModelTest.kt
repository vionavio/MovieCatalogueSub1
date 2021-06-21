package com.viona.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.vo.Resource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        val randomNumber = (0 until 100).random()
        Mockito.`when`(dummyMovies.data?.size).thenReturn(randomNumber)

        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        Mockito.`when`(repository.getMovie()).thenReturn(movies)
        val moviesEntity = viewModel.getMovie().value?.data

        verify(repository).getMovie()
        TestCase.assertNotNull(moviesEntity)
        TestCase.assertEquals(randomNumber, moviesEntity?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}