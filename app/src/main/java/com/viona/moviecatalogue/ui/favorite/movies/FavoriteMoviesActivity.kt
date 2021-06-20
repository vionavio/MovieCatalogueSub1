package com.viona.moviecatalogue.ui.favorite.movies

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.databinding.ActivityFavoriteMoviesBinding
import com.viona.moviecatalogue.ui.movie.MovieAdapter
import com.viona.moviecatalogue.ui.movie.detail.DetailMovieActivity
import com.viona.moviecatalogue.ui.movie.detail.MovieCallback
import com.viona.moviecatalogue.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesActivity : AppCompatActivity(), MovieCallback {

    private var _favoriteMoviesBinding: ActivityFavoriteMoviesBinding? = null
    private val binding get() = _favoriteMoviesBinding

    private val viewModel: FavoriteMoviesViewModel by viewModel()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _favoriteMoviesBinding = ActivityFavoriteMoviesBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()
    }

    private fun initView() {
        supportActionBar?.title = "Favorite Movies"
        adapter = MovieAdapter(this) { movie -> gotoResult(movie) }

        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getFavoriteMovies().observe(this, {
            binding?.progressBar?.visibility = View.GONE
            adapter.submitList(it)
        })
        binding?.rvMovies?.layoutManager = LinearLayoutManager(this)
        binding?.rvMovies?.setHasFixedSize(true)
        binding?.rvMovies?.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onShareClick(movie: MutableList<String?>) {

    }

    private fun gotoResult(movie: MovieEntity) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIE, movie.id)
        this.startActivity(intent)
    }
}