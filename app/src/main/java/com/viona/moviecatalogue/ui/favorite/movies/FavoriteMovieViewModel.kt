package com.viona.moviecatalogue.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.repository.MovieRepository
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val repository: MovieRepository) : ViewModel() {
    fun favoriteMovie(): LiveData<PagedList<MovieEntity>> {
        return repository.getFavoriteMovie()
    }
}