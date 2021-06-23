package com.viona.moviecatalogue.utils

import com.google.gson.Gson
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class DataDummy {
    fun getMovie(): MoviesResponse {
        return jsonData("movies.json", MoviesResponse::class.java)
    }

    fun getDetailMovie(): MovieResultsItem {
        return jsonData("movie.json", MovieResultsItem::class.java)
    }

    fun getTVShow(): TVShowsResponse {
        return jsonData("tv_shows.json", TVShowsResponse::class.java)
    }

    fun getDetailTVShow(): TVShowDetailResponse {
        return jsonData("tv_show.json", TVShowDetailResponse::class.java)
    }

    private fun <T> jsonData(filename: String, type: Class<T>): T {
        val testFolderResources = File(File("").absolutePath, "src/test/resources/com/viona/moviecatalogue/data/repository")
        val jsonFile = File(testFolderResources.absolutePath, filename)
        val iStream = FileInputStream(jsonFile)

        val iReader = InputStreamReader(iStream)
        val data = Gson().fromJson(iReader, type)

        iReader.close()
        iStream.close()

        return data
    }
}