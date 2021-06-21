package com.viona.moviecatalogue.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ilham.jpro.lastsubmission.LiveDataTestUtil
import com.ilham.jpro.lastsubmission.PagedListUtil
import com.viona.moviecatalogue.data.source.local.MovieLocalDataSource
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.data.source.remote.MovieRemoteDataSource
import com.viona.moviecatalogue.utils.AppExecutors
import com.viona.moviecatalogue.utils.DataDummy
import com.viona.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(MovieRemoteDataSource::class.java)
    private val local = mock(MovieLocalDataSource::class.java)
    private val appExecutor = mock(AppExecutors::class.java)
    private val dataDummy = DataDummy()

    private val repository = MovieRepository(remote, local, appExecutor)

    private val sampleMoviesResponse = dataDummy.getMovie()
    private val sampleMovieResponse = dataDummy.getDetailMovie()
    private val sampleMovieId = sampleMovieResponse.id!!
    private val sampleMovieEntity = MovieEntity.fromMovieResponse(sampleMovieResponse)

    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getDetailMovie()).thenReturn(dataSourceFactory)
        repository.getMovie()

        val moviesEntity = MovieEntity.fromMoviesResponse(dataDummy.getMovie())!!
        val moviesPaged = PagedListUtil.mockPagedList(moviesEntity)
        val moviesResource = Resource.success(moviesPaged)

        verify(local).getDetailMovie()
        assertNotNull(moviesResource.data)
        assertEquals(sampleMoviesResponse.results?.size, moviesResource.data?.size)
    }

    @Test
    fun getMovie() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        val movieEntity = MovieEntity.fromMovieResponse(sampleMovieResponse)
        dummyMovie.value = movieEntity
        `when`(local.getDetailMovie(sampleMovieId)).thenReturn(dummyMovie)

        val movieLive = LiveDataTestUtil.getValue(repository.getDetailMovie(sampleMovieId))
        verify(local).getDetailMovie(sampleMovieId)
        assertNotNull(movieLive)
        assertNotNull(movieLive.data)
        assertEquals(movieEntity, movieLive.data)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovie()

        val moviesEntity = MovieEntity.fromMoviesResponse(dataDummy.getMovie())!!
        val moviesPaged = PagedListUtil.mockPagedList(moviesEntity)
        val moviesResource = Resource.success(moviesPaged)

        verify(local).getFavoriteMovie()
        assertNotNull(moviesResource)
        assertEquals(sampleMoviesResponse.results?.size, moviesResource.data?.size)
    }

    @Test
    fun setFavoriteMovie() {
        local.setMovieFavorite(sampleMovieEntity, true)

        verify(local).setMovieFavorite(sampleMovieEntity, true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getFavoriteMoviesCount() {
        val randomNumber = MutableLiveData<Int>()
        randomNumber.value = (0 until 100).random()
        `when`(local.getFavoriteCount()).thenReturn(randomNumber)

        val countLive = LiveDataTestUtil.getValue(repository.getFavoriteMovieCount())
        verify(local).getFavoriteCount()
        assertEquals(randomNumber.value, countLive)
    }
}