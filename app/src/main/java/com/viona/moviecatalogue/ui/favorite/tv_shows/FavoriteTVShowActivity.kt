package com.viona.moviecatalogue.ui.favorite.tv_shows

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.databinding.ActivityFavoriteTvShowBinding
import com.viona.moviecatalogue.ui.tv_show.TVShowAdapter
import com.viona.moviecatalogue.ui.tv_show.detail.DetailTVShowActivity
import com.viona.moviecatalogue.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTVShowActivity : AppCompatActivity() {

    private var favoriteTVShowsBinding: ActivityFavoriteTvShowBinding? = null
    private val binding get() = favoriteTVShowsBinding

    private val viewModel: FavoriteTVShowViewModel by viewModel()
    private lateinit var adapter: TVShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteTVShowsBinding = ActivityFavoriteTvShowBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()
    }

    private fun initView() {
        supportActionBar?.title = getString(R.string.tv_show_favorite)
        adapter = TVShowAdapter(this) { tvShow -> gotoResult(tvShow) }

        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getFavoriteTVShow().observe(this, {
            binding?.progressBar?.visibility = View.GONE

            if (it.isNullOrEmpty()) {
                binding?.noTvShowFavorites?.visibility = View.VISIBLE
                binding?.rvTvShows?.visibility = View.GONE
            } else {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })
        binding?.rvTvShows?.layoutManager = LinearLayoutManager(this)
        binding?.rvTvShows?.setHasFixedSize(true)
        binding?.rvTvShows?.adapter = adapter
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

    private fun gotoResult(tvShow: TVShowEntity) {
        val intent = Intent(this, DetailTVShowActivity::class.java)
        intent.putExtra(Constants.EXTRA_TV_SHOW, tvShow.id)
        this.startActivity(intent)
    }
}