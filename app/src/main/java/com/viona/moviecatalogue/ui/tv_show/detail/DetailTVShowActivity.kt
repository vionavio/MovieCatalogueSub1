package com.viona.moviecatalogue.ui.tv_show.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.data.source.remote.response.GenresItem
import com.viona.moviecatalogue.data.source.remote.response.SpokenLanguagesItem
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp
import com.viona.moviecatalogue.utils.Notification
import com.viona.moviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTVShowActivity : AppCompatActivity(), TVShowCallback {

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private val mainBinding get() = activityDetailTvShowBinding

    private val detailTVShowViewModel: DetailTVShowViewModel by viewModel()
    private var tvShow = mutableListOf<String?>()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)
        initData()
        initUI()
    }

    private fun initData() {
        intent.extras?.let {
            val tvShowId = it.getInt(Constants.EXTRA_TV_SHOW)

            detailTVShowViewModel.apply {
                setTVShowId(tvShowId)

                tvShow.observe(this@DetailTVShowActivity, { tvShows ->
                    if (tvShows != null) {
                        /*  activityDetailTvShowBinding.progressBars.root.visibility = View.GONE
                          activityDetailTvShowBinding.scrollview.visibility = View.VISIBLE
                          getDetail(tvShows)
  */
                        when (tvShows.status) {
                            Status.LOADING -> mainBinding.progressBars.root.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> tvShows.data?.let { tvShow ->
                                mainBinding.progressBars.root.visibility = View.GONE

                                getDetail(tvShow)
                            }
                            Status.ERROR -> {
                                mainBinding.progressBars.root.visibility = View.GONE
                                Notification.showToast(
                                    this@DetailTVShowActivity,
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
        supportActionBar?.title = getString(R.string.tv_show)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getDetail(tvShows: TVShowEntity) {
        mainBinding.apply {
            tvShowTitle.text = tvShows.name
            tvOriginalName.text = tvShows.originalName
            tvLanguage.text = tvShows.originalLanguage
            tvShowRate.text = resources.getString(
                R.string.rates, tvShows.voteAverage, tvShows.voteCount
            )

            tvDesc.text = tvShows.overview
            tvPopularity.text = tvShows.popularity.toString()
            tvAirDate.text = tvShows.firstAirDate
            tvShow.apply {
                add(tvShows.name)
            }
        }

        imgPoster(tvShows)

        if (tvShows.backdrop_path != null) backdropPath(tvShows)
        else imgPoster(tvShows)
    }

    private fun backdropPath(tvShows: TVShowEntity) {
        if (tvShows.backdrop_path != null) {
            GlideApp.with(this)
                .load(Constants.IMAGE_URL + tvShows.backdrop_path)
                .centerCrop()
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading_backdrop)
                        .error(R.drawable.ic_error_backdrop)
                )
                .into(activityDetailTvShowBinding.tvBackdropPath)
        }
    }

    private fun imgPoster(tvShows: TVShowEntity) {
        GlideApp.with(this)
            .load(Constants.IMAGE_URL + tvShows.posterPath)
            .transform(RoundedCorners(Constants.ROUND_RADIUS))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(activityDetailTvShowBinding.imgPoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu

        detailTVShowViewModel.tvShow.observe(this, { tvShowResource ->
            tvShowResource.data?.let {
                val state = it.favorite
                setFavoriteState(state)
            }
        })

        return true
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                onShareClick(tvShow)
                true
            }
            R.id.action_favorite -> {
                detailTVShowViewModel.setFavorite()
                showNotification()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showNotification() {
        val isFavorite = detailTVShowViewModel.tvShow.value?.data?.favorite ?: false
        val message: String = if (isFavorite) {
            "Menghapus dari favorit..."
        } else {
            "Menambahkan ke favorit..."
        }
        mainBinding.root.let { Notification.showSnackbar(it, message) }
    }

    override fun onShareClick(tvShow: MutableList<String?>) {
        this.let {
            val mimeType = Constants.MIME_TYPE
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_tv_show, tvShow[0]))
                .startChooser()
        }
    }
}