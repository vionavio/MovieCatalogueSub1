package com.viona.moviecatalogue.ui.movie.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.viona.moviecatalogue.models.MovieEntity
import com.viona.moviecatalogue.utils.GlideApp

class DetailMovieActivity : AppCompatActivity() {
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
            .transform(RoundedCorners(16))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(activityDetailMovieBinding.imgPoster)
        val price = resources.getString(R.string.price, movies.price)
        activityDetailMovieBinding.buttonBuy.setOnClickListener {
            Toast.makeText(this, price, Toast.LENGTH_LONG).show()
        }
    }
}