package com.viona.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.repository.TVShowRepository

class FavoriteViewModel(
    private val movieRepo: MovieRepository,
    private val tvShowRepo: TVShowRepository
) : ViewModel() {

    fun getFavoriteMovieCount(): LiveData<Int> {
        return movieRepo.getFavoriteMovieCount()
    }

    fun getFavoriteTVShowCount(): LiveData<Int> {
        return tvShowRepo.getFavoriteTVShowCount()
    }
}