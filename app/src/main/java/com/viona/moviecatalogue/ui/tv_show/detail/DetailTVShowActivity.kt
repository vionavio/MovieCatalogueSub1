package com.viona.moviecatalogue.ui.tv_show.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.viona.moviecatalogue.models.TVShowEntity
import com.viona.moviecatalogue.utils.GlideApp

class DetailTVShowActivity : AppCompatActivity(), TVShowCallback {
    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var viewModel: DetailTVShowViewModel
    private lateinit var tvShow: TVShowEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(activityDetailTvShowBinding.root)
        initData()
    }

    private fun initData() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailTVShowViewModel::class.java]

        intent.extras?.let {
            val tvShowId = it.getString(EXTRA_TV_SHOW)
            if (tvShowId != null) {
                viewModel.setSelectedTVShow(tvShowId)
            }
            tvShow = viewModel.getTVShow()
            getDetail(tvShow)
        }
    }

    private fun getDetail(tvShows: TVShowEntity) {
        activityDetailTvShowBinding.tvShowTitle.text = tvShows.title
        activityDetailTvShowBinding.tvYear.text = tvShows.year.toString()
        activityDetailTvShowBinding.tvShowRate.text = resources.getString(
            R.string.rate, tvShows.rating
        )
        activityDetailTvShowBinding.tvEpisodes.text = resources.getString(
            R.string.episode, tvShows.episode
        )
        activityDetailTvShowBinding.tvTypeShow.text = tvShows.type
        activityDetailTvShowBinding.tvActor.text = tvShows.star
        activityDetailTvShowBinding.tvDesc.text = tvShows.description


        GlideApp.with(this)
            .load(tvShows.imagePath)
            .transform(RoundedCorners(18))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(activityDetailTvShowBinding.imgPoster)

        val price = resources.getString(R.string.price, tvShows.price)
        activityDetailTvShowBinding.buttonBuy.text = price
        activityDetailTvShowBinding.buttonBuy.setOnClickListener {
            Toast.makeText(this, price, Toast.LENGTH_LONG).show()
        }
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


    override fun onShareClick(tvShow: TVShowEntity) {
        this.let {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_movie, tvShow.title))
                .startChooser()
        }
    }
}