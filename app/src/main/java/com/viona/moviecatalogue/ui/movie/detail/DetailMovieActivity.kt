package com.viona.moviecatalogue.ui.movie.detail

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
import com.viona.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.viona.moviecatalogue.models.MovieEntity
import com.viona.moviecatalogue.utils.GlideApp


class DetailMovieActivity : AppCompatActivity(), MovieCallback {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private lateinit var movie: MovieEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        initData()
        initUI()
    }

    private fun initData() {
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]

        intent.extras?.let {
            val movieId = it.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
            }
            movie = viewModel.getMovie()
            getDetail(movie)
        }
    }

    private fun initUI() {
        supportActionBar?.title = movie.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getDetail(movies: MovieEntity) {
        activityDetailMovieBinding.tvItemTitle.text = movies.title
        activityDetailMovieBinding.tvYear.text = movies.year.toString()
        activityDetailMovieBinding.tvDetailRate.text = resources.getString(
            R.string.rate, movies.rating
        )
        activityDetailMovieBinding.tvTomato.text = resources.getString(
            R.string.percent, movies.tomatometer
        )
        activityDetailMovieBinding.tvSumRate.text = movies.people_rate.toString()
        activityDetailMovieBinding.tvDuration.text = movies.duration
        activityDetailMovieBinding.tvDesctiption.text = movies.description
        activityDetailMovieBinding.tvDirectors.text = movies.director
        activityDetailMovieBinding.tvStars.text = movies.stars
        activityDetailMovieBinding.tvWriters.text = movies.writers
        activityDetailMovieBinding.buttonBuy.text = resources.getString(
            R.string.price, movies.price
        )

        GlideApp.with(this)
            .load(movies.imagePath)
            .transform(RoundedCorners(18))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(activityDetailMovieBinding.imgPoster)
        val price = resources.getString(R.string.price, movies.price)
        activityDetailMovieBinding.buttonBuy.setOnClickListener {
            Toast.makeText(this, price, Toast.LENGTH_SHORT).show()
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
                onShareClick(movie)
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

    override fun onShareClick(movie: MovieEntity) {
        this.let {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_movie, movie.title))
                .startChooser()
        }
    }
}