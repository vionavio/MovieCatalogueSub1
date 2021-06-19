package com.viona.moviecatalogue.injection

import com.viona.moviecatalogue.ui.favorite.FavoriteViewModel
import com.viona.moviecatalogue.ui.favorite.movies.FavoriteMoviesViewModel
import com.viona.moviecatalogue.ui.favorite.tv_shows.FavoriteTVShowsViewModel
import com.viona.moviecatalogue.ui.movie.MovieViewModel
import com.viona.moviecatalogue.ui.movie.detail.DetailMovieViewModel
import com.viona.moviecatalogue.ui.tv_show.TVShowViewModel
import com.viona.moviecatalogue.ui.tv_show.detail.DetailTVShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }

    viewModel { TVShowViewModel(get()) }
    viewModel { DetailTVShowViewModel(get()) }

    viewModel { FavoriteViewModel(get(), get()) }

    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { FavoriteTVShowsViewModel(get()) }
}