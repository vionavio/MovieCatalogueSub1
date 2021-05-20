package com.viona.moviecatalogue.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var dataMovie: MovieDetailResponse
    private var movieId: Int = 0

    @Mock
    private lateinit var repository: DataRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<MovieDetailResponse?>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(repository)
    }

    @Test
    fun getMovie() {

        dataMovie = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("movie.json")),
            MovieDetailResponse::class.java
        )
        val movieLive = MutableLiveData<MovieDetailResponse>()
        movieLive.value = dataMovie


        dataMovie.id?.let { movieId = it }
        Mockito.`when`(repository.getMovieDetail(movieId))
            .thenReturn(MutableLiveData(dataMovie))

        viewModel.setMovieId(movieId)
        viewModel.getMovieDetail()
        val movie = viewModel.movie.value

        assertNotNull(movie)
        assertEquals(dataMovie.id, movie?.id)
        assertEquals(dataMovie.title, movie?.title)
        assertEquals(dataMovie.tagline, movie?.tagline)
        assertEquals(dataMovie.voteAverage, movie?.voteAverage)
        assertEquals(dataMovie.popularity, movie?.popularity)
        assertEquals(dataMovie.voteCount, movie?.voteCount)
        assertEquals(dataMovie.status, movie?.status)
        assertEquals(dataMovie.overview, movie?.overview)
        assertEquals(dataMovie.genres, movie?.genres)
        assertEquals(dataMovie.releaseDate, movie?.releaseDate)
        assertEquals(dataMovie.spokenLanguages, movie?.spokenLanguages)
        assertEquals(dataMovie.posterPath, movie?.posterPath)
        assertEquals(dataMovie.budget, movie?.budget)

        viewModel.movie.observeForever(observer)
        verify(observer).onChanged(dataMovie)
    }
}