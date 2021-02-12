package com.viona.moviecatalogue.models

data class MovieEntity(
    var movieId: String,
    var title: String,
    var year: Int,
    var description: String,
    var director: String,
    var writers: String,
    var stars: String,
    var rating: Double,
    var imagePath: String
)