package com.viona.moviecatalogue.ui.favorite.tv_shows

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowResultsItem
import com.viona.moviecatalogue.databinding.ActivityFavoriteTvShowsBinding
import com.viona.moviecatalogue.ui.movie.MovieAdapter
import com.viona.moviecatalogue.ui.tv_show.TVShowAdapter
import com.viona.moviecatalogue.ui.tv_show.detail.DetailTVShowActivity
import com.viona.moviecatalogue.ui.tv_show.detail.TVShowCallback
import com.viona.moviecatalogue.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTVShowsActivity : AppCompatActivity(), TVShowCallback {

    private var _favoriteTVShowsBinding: ActivityFavoriteTvShowsBinding? = null
    private val binding get() = _favoriteTVShowsBinding

    private val viewModel: FavoriteTVShowsViewModel by viewModel()
    private lateinit var adapter: TVShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _favoriteTVShowsBinding = ActivityFavoriteTvShowsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()
    }

    private fun initView() {
        supportActionBar?.title = "Favorite TV Shows"
        adapter = TVShowAdapter(this) { tvShow -> gotoResult(tvShow) }

        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getFavoriteTVShows().observe(this, {
            binding?.progressBar?.visibility = View.GONE
            adapter.submitList(it)
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

    /*override fun onShareClick(tvShow: TVShowEntity?) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
           *//* .setChooserTitle(getString(R.string.share_title))
            .setText(resources.getString(R.string.share_text, tvShow?.name))*//*
            .startChooser()
    }*/

    private fun gotoResult(tvShow: TVShowEntity) {
        val intent = Intent(this, DetailTVShowActivity::class.java)
        intent.putExtra(Constants.EXTRA_TV_SHOW, tvShow.id)
        this.startActivity(intent)
    }

    override fun onShareClick(tvShow: MutableList<String?>) {

    }
}