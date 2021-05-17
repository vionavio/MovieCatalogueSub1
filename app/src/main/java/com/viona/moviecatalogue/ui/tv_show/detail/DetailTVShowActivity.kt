package com.viona.moviecatalogue.ui.tv_show.detail

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.remote.response.GenresItem
import com.viona.moviecatalogue.data.source.remote.response.SpokenLanguagesItem
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowResultsItem
import com.viona.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTVShowActivity : AppCompatActivity(), TVShowCallback {

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private val detailTVShowViewModel: DetailTVShowViewModel by viewModel()
    private lateinit var tvShow: TVShowResultsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)
        initData()
        initUI()
    }

    private fun initData() {
        tvShow = TVShowResultsItem()

        intent.extras?.let {
            val tvShowId = it.getInt(Constants.EXTRA_TV_SHOW)

            detailTVShowViewModel.setTVShowId(tvShowId)
            detailTVShowViewModel.getTVShowDetail()
            detailTVShowViewModel.tvShow.observe(this, { tvShows ->
                if (tvShows != null) {
                    getDetail(tvShows)
                }
            })

        }
    }

    private fun initUI() {
        //supportActionBar?.title = tvShow.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getDetail(tvShows: TVShowDetailResponse) {
        activityDetailTvShowBinding.apply {
            tvShowTitle.text = tvShows.name
            tvOriginalName.text = tvShows.originalName
            tvShowRate.text = resources.getString(
                R.string.rates, tvShows.voteAverage, tvShows.voteCount
            )
            tvEpisode.text = resources.getString(
                R.string.episodes_seasons,
                tvShows.numberOfEpisodes,
                tvShows.numberOfSeasons
            )
            val spokenLanguage = StringBuilder()
            val nameLanguage = StringBuilder()
            val languageItem: List<SpokenLanguagesItem?>? = tvShows.spokenLanguages
            if (languageItem != null) {
                for (language in languageItem) {
                    spokenLanguage.append(language?.englishName + "  ")
                    nameLanguage.append(language?.name + "   ")
                }
            }
            tvLanguage.text = resources.getString(R.string.language, spokenLanguage, nameLanguage)
            val genreMovie = StringBuilder()
            val genreList: List<GenresItem?>? = tvShows.genres
            if (genreList != null) {
                for (genre in genreList) {
                    genreMovie.append(genre?.name.toString() + "\n")
                }
            }
            tvGenreShow.text = genreMovie
            tvDesc.text = tvShows.overview
            tvPopularity.text = tvShows.popularity.toString()
            if (tvShows.status == getString(R.string.ended)) {
                tvStatus.setTextColor(Color.RED)
            }
            tvStatus.text = tvShows.status
            tvAirDate.text = tvShows.firstAirDate

            rvPoster.adapter = PosterAdapter(this@DetailTVShowActivity, tvShows.seasons)
        }

        GlideApp.with(this)
            .load(Constants.IMAGE_URL + tvShows.posterPath)
            .transform(RoundedCorners(Constants.ROUND_RADIUS))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(activityDetailTvShowBinding.imgPoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(
            0, 1, 1,
            ContextCompat.getDrawable(this, R.drawable.ic_share)?.let {
                menuIconWithText(
                    it,
                    resources.getString(R.string.share)
                )
            }
        )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            1 -> {
                onShareClick(tvShow)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun menuIconWithText(r: Drawable, title: String): CharSequence {
        r.setBounds(0, 0, r.intrinsicWidth, r.intrinsicHeight)
        val sb = SpannableString("    $title")
        val imageSpan = ImageSpan(r, ImageSpan.ALIGN_BOTTOM)
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sb
    }

    override fun onShareClick(tvShow: TVShowResultsItem) {
        this.let {
            val mimeType = Constants.MIME_TYPE
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_movie, tvShow.name))
                .startChooser()
        }
    }
}