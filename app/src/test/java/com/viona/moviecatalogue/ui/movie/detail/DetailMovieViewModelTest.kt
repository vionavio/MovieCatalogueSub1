package com.viona.moviecatalogue.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
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
class MovieDetailViewModelTest {
    private val dataDummy = DataDummy()
    private lateinit var viewModel: DetailMovieViewModel
    private val sampleMovie = dataDummy.getDetailMovie()
    private val sampleMovieId = sampleMovie.id!!
    private val movieEntity = MovieEntity.fromMovieResponse(sampleMovie)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>?>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(repository)
        viewModel.setMovieId(sampleMovieId)
    }

    @Test
    fun getMovie() {
        val movieResource = Resource.success(movieEntity)
        val movieLive = MutableLiveData<Resource<MovieEntity>>()
        movieLive.value = movieResource

        Mockito.`when`(repository.getDetailMovie(sampleMovieId)).thenReturn(movieLive)

        viewModel.movie.observeForever(observer)
        verify(observer).onChanged(movieResource)

        val movie = viewModel.movie.value?.data!!

        assertNotNull(movie)

        assertEquals(sampleMovie.id, movie.id)
        assertEquals(sampleMovie.title, movie.title)
        assertEquals(sampleMovie.overview, movie.overview)
        assertEquals(sampleMovie.posterPath, movie.posterPath)
        assertEquals(sampleMovie.releaseDate, movie.releaseDate)
        assertEquals(sampleMovie.voteCount, movie.voteCount)

        assertEquals(sampleMovie.popularity as Double, movie.popularity, 0.0001)
        assertEquals(sampleMovie.voteAverage as Double, movie.voteAverage, 0.0001)
    }
}