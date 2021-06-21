package com.viona.moviecatalogue.ui.favorite.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.databinding.ActivityFavoriteMovieBinding
import com.viona.moviecatalogue.ui.movie.MovieAdapter
import com.viona.moviecatalogue.ui.movie.detail.DetailMovieActivity
import com.viona.moviecatalogue.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieActivity : AppCompatActivity() {

    private var favoriteMoviesBinding: ActivityFavoriteMovieBinding? = null
    private val binding get() = favoriteMoviesBinding

    private val viewModel: FavoriteMovieViewModel by viewModel()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteMoviesBinding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initUI()
    }

    private fun initUI() {
        supportActionBar?.title =getString(R.string.movie_favorite)
        adapter = MovieAdapter(this) { movie -> gotoResult(movie) }

        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.favoriteMovie().observe(this, {
            binding?.progressBar?.visibility = View.GONE
            if (it.isNullOrEmpty()) {
                binding?.noMovieFavorites?.visibility = View.VISIBLE
                binding?.rvMovies?.visibility = View.GONE
            } else {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })
        binding?.rvMovies?.layoutManager = LinearLayoutManager(this)
        binding?.rvMovies?.setHasFixedSize(true)
        binding?.rvMovies?.adapter = adapter
    }

    private fun initData(){}

    private fun gotoResult(movie: MovieEntity) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIE, movie.id)
        this.startActivity(intent)
    }
}