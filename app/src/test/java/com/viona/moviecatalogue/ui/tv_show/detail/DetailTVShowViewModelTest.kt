package com.viona.moviecatalogue.ui.tv_show.detail

import com.viona.moviecatalogue.data.DataTVShow
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailTVShowViewModelTest {

    private lateinit var viewModel: DetailTVShowViewModel
    private val dataTVShow = DataTVShow.generateDummyTVShow()[0]
    private val tvShowId = dataTVShow.tvShowId

    @Before
    fun setUp() {
        viewModel = DetailTVShowViewModel()
        viewModel.setSelectedTVShow(tvShowId)
    }

    @Test
    fun setSelectedTVShow() {
    }

    @Test
    fun getTVShow() {
        viewModel.setSelectedTVShow(dataTVShow.tvShowId)
        val tvShowEntity = viewModel.getTVShow()
        assertNotNull(tvShowEntity)
        assertEquals(dataTVShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dataTVShow.title, tvShowEntity.title)
        assertEquals(dataTVShow.year, tvShowEntity.year)
        assertEquals(dataTVShow.star, tvShowEntity.star)
        assertEquals(dataTVShow.description, tvShowEntity.description)
        assertEquals(dataTVShow.type, tvShowEntity.type)
        assertEquals(dataTVShow.episode, tvShowEntity.episode)
        assertEquals(dataTVShow.rating, tvShowEntity.rating, 0.0001)
        assertEquals(dataTVShow.price, tvShowEntity.price)
        assertEquals(dataTVShow.imagePath, tvShowEntity.imagePath)
    }
}