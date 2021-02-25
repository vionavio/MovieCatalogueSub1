package com.viona.moviecatalogue.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.viona.moviecatalogue.data.source.remote.RemoteDataSource
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import java.io.InputStreamReader

class DataRepositoryTest {
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val repository = DataRepository(remoteDataSource)

    @Test
    fun getMovies() {
        val sampleMovies = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("movies.json")),
            MoviesResponse::class.java
        )
        Mockito.`when`(remoteDataSource.getMovies()).thenReturn(MutableLiveData(sampleMovies))
        val movies = repository.getMovies()

        Mockito.verify(remoteDataSource).getMovies()
        assertEquals(sampleMovies, movies.value)
    }

    @Test
    fun getMovieDetail() {
        val sampleMovie = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_movie.json")),
            MovieDetailResponse::class.java
        )
        var sampleMovieId: Int
        sampleMovie?.id.let { sampleMovieId = it ?: 0 }

        Mockito.`when`(remoteDataSource.getMovieDetail(sampleMovieId)).thenReturn(
            MutableLiveData(
                sampleMovie
            )
        )
        val movie = repository.getMovieDetail(sampleMovieId)

        Mockito.verify(remoteDataSource).getMovieDetail(sampleMovieId)
        assertEquals(sampleMovie, movie.value)
    }

    @Test
    fun getTVShows() {
        val sampleTVShows = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_tv_shows.json")),
            TVShowsResponse::class.java
        )
        Mockito.`when`(remoteDataSource.getTVShows()).thenReturn(MutableLiveData(sampleTVShows))
        val tvShows = repository.getTVShows()

        Mockito.verify(remoteDataSource).getTVShows()
        assertEquals(sampleTVShows, tvShows.value)
    }

    @Test
    fun getTVShowDetail() {
        val sampleTVShow = Gson().fromJson(
            InputStreamReader(javaClass.getResourceAsStream("get_tv_show.json")),
            TVShowDetailResponse::class.java
        )
        var sampleTVShowId: Int
        sampleTVShow?.id.let { sampleTVShowId = it ?: 0 }

        Mockito.`when`(remoteDataSource.getTVShowDetail(sampleTVShowId)).thenReturn(
            MutableLiveData(
                sampleTVShow
            )
        )
        val tvShow = repository.getTVShowDetail(sampleTVShowId)

        Mockito.verify(remoteDataSource).getTVShowDetail(sampleTVShowId)
        assertEquals(sampleTVShow, tvShow.value)
    }
}
