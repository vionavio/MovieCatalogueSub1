package com.viona.moviecatalogue.ui.movie.detail

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
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.viona.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovieActivity : AppCompatActivity(), MovieCallback {

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var movie: MovieResultsItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        initData()
        initUI()
    }

    private fun initData() {

        intent.extras?.let {
            val movieId = it.getInt(Constants.EXTRA_MOVIE)

            detailMovieViewModel.setMovieId(movieId)
            detailMovieViewModel.getMovieDetail()
            detailMovieViewModel.movie.observe(this, { movies ->
                if (movies != null) {
                    getDetail(movies)
                }
            })
        }
    }

    private fun initUI() {
        // supportActionBar?.title = movie.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getDetail(movies: MovieDetailResponse) {
        activityDetailMovieBinding.apply {
            tvItemTitle.text = movies.title
            tvDetailRate.text = resources.getString(
                R.string.rate, movies.voteAverage
            )
            tvDesctiption.text = movies.overview
            tvSumRate.text = movies.voteCount.toString()
            tvPopularity.text =  movies.popularity.toString()
            if (movies.status == getString(R.string.released)) {
                tvStatus.setTextColor(Color.RED)
            }
            tvStatus.text = movies.status
            tvGenre.text = movies.genres.toString()
            tvDateRelase.text = movies.releaseDate
            tvLanguage.text = movies.originalLanguage

            val builder = StringBuilder()
            val genreList: List<GenresItem?>? = movies.genres
            if (genreList != null) {
                for (genre in genreList) {
                    builder.append(genre?.name.toString() + "  ")
                }
            }
            tvGenre.text = builder
        }


        /* activityDetailMovieBinding.tvYear.text = movies.year.toString()
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
 */
        GlideApp.with(this)
            .load("https://image.tmdb.org/t/p/w500/${movies.posterPath}")
            .transform(RoundedCorners(Constants.ROUND_RADIUS))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(activityDetailMovieBinding.imgPoster)
        //val price = resources.getString(R.string.price, movies.price)
        /* activityDetailMovieBinding.buttonBuy.setOnClickListener {
             Toast.makeText(this, price, Toast.LENGTH_SHORT).show()
         }*/
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

    override fun onShareClick(movie: MovieResultsItem) {
        this.let {
            val mimeType = Constants.MIME_TYPE
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(resources.getString(R.string.share_movie, movie.title))
                .startChooser()
        }
    }
}