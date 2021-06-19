package com.viona.moviecatalogue.ui.favorite.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.viona.moviecatalogue.data.repository.TVShowRepository
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity

class FavoriteTVShowsViewModel(private val repository: TVShowRepository) : ViewModel() {
    fun getFavoriteTVShows(): LiveData<PagedList<TVShowEntity>> {
        return repository.getFavoriteTVShows()
    }
}