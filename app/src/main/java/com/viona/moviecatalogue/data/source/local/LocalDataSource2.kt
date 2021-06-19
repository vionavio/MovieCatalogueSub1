package com.viona.moviecatalogue.data.source.local

import com.google.gson.Gson
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class LocalDataSource2 {
    fun getMovies(): MoviesResponse {
        return getFromJson("movies.json", MoviesResponse::class.java)
    }

    fun getMovie(): DetailMovieResponse {
        return getFromJson("movie.json", DetailMovieResponse::class.java)
    }

    fun getTVShows(): TVShowsResponse {
        return getFromJson("tv_shows.json", TVShowsResponse::class.java)
    }

    fun getTVShow(): TVShowDetailResponse {
        return getFromJson("tv_show.json", TVShowDetailResponse::class.java)
    }

    private fun <T> getFromJson(filename: String, type: Class<T>): T {
        val testFolderResources = File(File("").absolutePath, "src/test/resources")
        val jsonFile = File(testFolderResources.absolutePath, filename)
        val iStream = FileInputStream(jsonFile)

        val iReader = InputStreamReader(iStream)
        val data = Gson().fromJson(iReader, type)

        iReader.close()
        iStream.close()

        return data
    }
}