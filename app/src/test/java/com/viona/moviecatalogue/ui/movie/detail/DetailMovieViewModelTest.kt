package com.viona.moviecatalogue.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.viona.moviecatalogue.data.repository.DataRepository
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var dataMovie: MovieDetailResponse
   // private val dataMovie = DataMovie.generateDummyMovie()[0]
    private var movieId : Int = 0

    @Mock
    private lateinit var repository: DataRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<MovieDetailResponse?>

    @Before
    fun setUp(){
        viewModel = DetailMovieViewModel(repository)
        //viewModel.setSelectedMovie(movieId)
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
        assertEquals(dataMovie.overview, movie?.overview)
        assertEquals(dataMovie.posterPath, movie?.posterPath)
        assertEquals(dataMovie.releaseDate, movie?.releaseDate)
        assertEquals(dataMovie.voteCount, movie?.voteCount)

        assertEquals(dataMovie.popularity as Double, movie?.popularity as Double, 0.0001)
        assertEquals(dataMovie.voteAverage as Double, movie.voteAverage as Double, 0.0001)

        viewModel.movie.observeForever(observer)
        verify(observer).onChanged(dataMovie)

        /*viewModel.setSelectedMovie(dataMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dataMovie.movieId, movieEntity.movieId)
        assertEquals(dataMovie.title, movieEntity.title)
        assertEquals(dataMovie.year, movieEntity.year)
        assertEquals(dataMovie.description, movieEntity.description)
        assertEquals(dataMovie.director, movieEntity.director)
        assertEquals(dataMovie.writers, movieEntity.writers)
        assertEquals(dataMovie.stars, movieEntity.stars)
        assertEquals(dataMovie.rating, movieEntity.rating, 0.0001)
        assertEquals(dataMovie.imagePath, movieEntity.imagePath)
        assertEquals(dataMovie.tomatometer, movieEntity.tomatometer)
        assertEquals(dataMovie.people_rate, movieEntity.people_rate)
        assertEquals(dataMovie.duration, movieEntity.duration)
        assertEquals(dataMovie.price, movieEntity.price)*/
    }
}