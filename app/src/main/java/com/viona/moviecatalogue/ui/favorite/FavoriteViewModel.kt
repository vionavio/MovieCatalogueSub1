package com.viona.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.repository.TVShowRepository

class FavoriteViewModel(
    private val movieRepository: MovieRepository,
    private val tvShowRepository: TVShowRepository
) : ViewModel() {

    fun getFavoriteMoviesCount(): LiveData<Int> {
        return movieRepository.getFavoriteMoviesCount()
    }

    fun getFavoriteTVShowsCount(): LiveData<Int> {
        return tvShowRepository.getFavoriteTVShowsCount()
    }
}