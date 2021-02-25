package com.viona.moviecatalogue.data.source.local

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        localDataSource = LocalDataSource()
    }

    @Test
    fun getMovies() {
        assertNotNull(localDataSource.getMovies())
    }

    @Test
    fun getMovie() {
        assertNotNull(localDataSource.getMovieDetail())
    }

    @Test
    fun getTVShows() {
        assertNotNull(localDataSource.getTVShows())
    }

    @Test
    fun getTVShow() {
        assertNotNull(localDataSource.getTVShowDetail())
    }
}