package com.viona.moviecatalogue.ui.tv_show.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.viona.moviecatalogue.data.repository.TVShowRepository
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.vo.Resource

class DetailTVShowViewModel(private val repository: TVShowRepository) : ViewModel() {
    private var id = MutableLiveData<Int>()

    fun setTVShowId(id: Int) {
        this.id.value = id
    }

    var tvShow: LiveData<Resource<TVShowEntity>> =
        Transformations.switchMap(id) { mId ->
            repository.getTVShow(mId)
        }

    fun setFavorite() {
        val tvShowResource = tvShow.value

        if (tvShowResource != null) {
            val tvShowData = tvShowResource.data

            tvShowData?.let {
                repository.setFavoriteTVShow(it, !it.favorite)
            }
        }
    }
}