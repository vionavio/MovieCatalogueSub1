package com.viona.moviecatalogue.ui.tv_show

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @Before
    fun setUp() {
        viewModel = TVShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShowEntities = viewModel.getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(12, tvShowEntities.size)
    }
}