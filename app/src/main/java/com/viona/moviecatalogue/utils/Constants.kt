package com.viona.moviecatalogue.utils


interface Constants {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val RESOURCE = "GLOBAL"

        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
        const val API_KEY = "api_key"

        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

        const val ROUND_RADIUS = 18
        const val TIMEOUT : Long = 30
        const val ZERO = 0
        const val ONE = 1

        const val USD = "USD"

        const val MIME_TYPE = "text/plain"
    }
}