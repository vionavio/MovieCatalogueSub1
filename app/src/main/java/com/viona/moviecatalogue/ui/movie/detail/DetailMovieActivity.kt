package com.viona.moviecatalogue.ui.movie.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.local.entity.MovieEntity
import com.viona.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp
import com.viona.moviecatalogue.utils.Notification
import com.viona.moviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class DetailMovieActivity : AppCompatActivity(), MovieCallback {

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private val mainBinding get() = activityDetailMovieBinding

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private var movie = mutableListOf<String?>()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initData()
        initUI()
    }

    private fun initData() {
        intent.extras?.let {
            val movieId = it.getInt(Constants.EXTRA_MOVIE)

            detailMovieViewModel.apply {
                setMovieId(movieId)

                movie.observe(this@DetailMovieActivity, { movies ->

                    if (movies != null) {
                        when (movies.status) {
                            Status.LOADING -> mainBinding.progressBars.root.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> movies.data?.let { movie ->
                                mainBinding.progressBars.root.visibility = View.GONE
                                mainBinding.scrollview.visibility = View.VISIBLE

                                getDetail(movie)
                            }
                            Status.ERROR -> {
                                mainBinding.progressBars.root.visibility = View.GONE
                                Notification.showToast(
                                    this@DetailMovieActivity,
                                    "Terjadi kesalahan"
                                )
                            }
                        }
                    }
                })
            }
        }
    }

    private fun initUI() {
        supportActionBar?.title = getString(R.string.movie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getDetail(movies: MovieEntity) {
        activityDetailMovieBinding.apply {
            tvItemTitle.text = movies.title
            tvOriginalTitle.text = movies.originalTitle
            tvDetailRate.text = resources.getString(
                R.string.rate, movies.voteAverage
            )
            tvPopularity.text = movies.popularity.toString()
            tvSumRate.text = movies.voteCount.toString()
            tvDescription.text = movies.overview
            tvDateRelease.text = movies.releaseDate
            tvLanguage.text = movies.originalLanguage

            movie.apply {
                add(movies.title)
            }
        }
        GlideApp.with(this)
            .load(Constants.IMAGE_URL + movies.backdrop_path)
            .centerCrop()
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading_backdrop)
                    .error(R.drawable.ic_error_backdrop)
            )
            .into(activityDetailMovieBinding.backdropPath)

        GlideApp.with(this)
            .load(Constants.IMAGE_URL + movies.posterPath)
            .transform(RoundedCorners(Constants.ROUND_RADIUS))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(activityDetailMovieBinding.imgPoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu
        detailMovieViewModel.movie.observe(this, { movieResource ->
            movieResource.data?.let {
                val state = it.favorite
                setFavoriteState(state)
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                onShareClick(movie)
                true
            }
            R.id.action_favorite -> {
                detailMovieViewModel.setFavorite()
                showNotification()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavoriteState(state: Boolean) {
        menu?.let {
            val menuItem = it.findItem(R.id.action_favorite)
            if (state) {
                menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited_white)
            } else {
                menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
            }
        }
    }

    private fun showNotification() {
        val isFavorite = detailMovieViewModel.movie.value?.data?.favorite ?: false
        val message: String = if (isFavorite) {
            "Menghapus dari favorit..."
        } else {
            "Menambahkan ke favorit..."
        }
        mainBinding.root.let { Notification.showSnackbar(it, message) }
    }

    override fun onShareClick(movie: MutableList<String?>) {
        this.let {
            val mimeType = Constants.MIME_TYPE
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_movie, movie[0]))
                .startChooser()
        }
    }
}