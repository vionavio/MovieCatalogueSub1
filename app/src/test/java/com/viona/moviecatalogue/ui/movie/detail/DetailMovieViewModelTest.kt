package com.viona.moviecatalogue.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.utils.DataDummy
import com.viona.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private val dataDummy = DataDummy()
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = dataDummy.getDetailMovie()
    private val dummyMovieId = dummyMovie.id
    private val movieEntity = MovieEntity.fromMovieResponse(dummyMovie)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>?>


    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(repository)
        if (dummyMovieId != null) {
            viewModel.setMovieId(dummyMovieId)
        }
    }

    @Test
    fun getMovie() {
        val movieResource = Resource.success(movieEntity)
        val movieLive = MutableLiveData<Resource<MovieEntity>>()
        movieLive.value = movieResource

        Mockito.`when`(dummyMovieId?.let { repository.getDetailMovie(it) }).thenReturn(movieLive)

        viewModel.movie.observeForever(observer)
        verify(observer).onChanged(movieResource)

        val movie = viewModel.movie.value?.data

        assertNotNull(movie)

        assertEquals(dummyMovie.id, movie?.id)
        assertEquals(dummyMovie.title, movie?.title)
        assertEquals(dummyMovie.originalTitle, movie?.originalTitle)
        assertEquals(dummyMovie.overview, movie?.overview)
        assertEquals(dummyMovie.posterPath, movie?.posterPath)
        assertEquals(dummyMovie.backdropPath, movie?.backdrop_path)
        assertEquals(dummyMovie.originalLanguage, movie?.originalLanguage)
        assertEquals(dummyMovie.voteCount, movie?.voteCount)

        movie?.let { assertEquals(dummyMovie.popularity as Double, it.popularity, 0.0001) }
        movie?.let { assertEquals(dummyMovie.voteAverage as Double, it.voteAverage, 0.0001) }
    }

}