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
import com.viona.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTVShowActivity : AppCompatActivity(), TVShowCallback {

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private val detailTVShowViewModel: DetailTVShowViewModel by viewModel()
    private var tvShow = mutableListOf<String?>()

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
                getTVShowDetail()
                tvShow.observe(this@DetailTVShowActivity, { tvShows ->
                    if (tvShows != null) {
                        getDetail(tvShows)
                    }
                })
            }
        }
    }

    private fun initUI() {
        supportActionBar?.title = getString(R.string.tv_show)
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
            tvLanguage.text = getLanguage(tvShows)
            tvGenreShow.text = getGenre(tvShows.genres)
            tvDesc.text = tvShows.overview
            tvPopularity.text = tvShows.popularity.toString()
            if (tvShows.status == getString(R.string.ended)) tvStatus.setTextColor(Color.RED)
            tvStatus.text = tvShows.status
            tvAirDate.text = tvShows.firstAirDate

            rvPoster.adapter = PosterAdapter(this@DetailTVShowActivity, tvShows.seasons)

            tvShow.apply {
                add(tvShows.name)
                add(tvShows.homepage)
            }
        }

        GlideApp.with(this)
            .load(Constants.IMAGE_URL + tvShows.posterPath)
            .transform(RoundedCorners(Constants.ROUND_RADIUS))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(activityDetailTvShowBinding.imgPoster)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(
            Constants.ZERO, Constants.ONE, Constants.ONE,
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
        r.setBounds(Constants.ZERO, Constants.ZERO, r.intrinsicWidth, r.intrinsicHeight)
        val sb = SpannableString("    $title")
        val imageSpan = ImageSpan(r, ImageSpan.ALIGN_BOTTOM)
        sb.setSpan(imageSpan, Constants.ZERO, Constants.ONE, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return sb
    }

    override fun onShareClick(tvShow: MutableList<String?>) {
        this.let {
            val mimeType = Constants.MIME_TYPE
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_tv_show, tvShow[0], tvShow[1]))
                .startChooser()
        }
    }

    private fun getLanguage(tvShows: TVShowDetailResponse): String {
        val spokenLanguage = StringBuilder()
        val nameLanguage = StringBuilder()
        val languageItem: List<SpokenLanguagesItem?>? = tvShows.spokenLanguages
        if (languageItem != null) {
            for (language in languageItem) {
                spokenLanguage.append(language?.englishName + "  ")
                nameLanguage.append(language?.name + "   ")
            }
        }
        return spokenLanguage.append(nameLanguage).toString()
    }

    private fun getGenre(genres: List<GenresItem?>?): String {
        val genreMovie = StringBuilder()
        val genreList: List<GenresItem?>? = genres
        if (genreList != null) {
            for (genre in genreList) {
                genreMovie.append(genre?.name.toString() + "\n")
            }
        }
        return genreMovie.toString()
    }
}