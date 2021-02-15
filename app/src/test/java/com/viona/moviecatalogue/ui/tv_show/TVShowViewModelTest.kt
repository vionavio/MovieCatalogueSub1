package com.viona.moviecatalogue.ui.tv_show

import com.viona.moviecatalogue.models.TVShowEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @Before
    fun setUp() {
        viewModel = TVShowViewModel()
    }

    @Test
    fun getTVShow() {
        val tvShowEntities = viewModel.getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(12, tvShowEntities.size)
    }

    @Test
    fun emptyTVShow() {
        val tvShowEntities = listOf<TVShowEntity>()
        assertFalse(tvShowEntities.isNotEmpty())
        assertNotEquals(12, tvShowEntities.size)
    }
}