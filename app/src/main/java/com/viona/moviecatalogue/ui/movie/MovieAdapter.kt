package com.viona.moviecatalogue.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.viona.moviecatalogue.databinding.ItemsMovieBinding
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp

class MovieAdapter(
    val context: Context,
    private val onItemClickListener: (MovieResultsItem) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<MovieResultsItem?>()

    fun setMovies(movies: ArrayList<MovieResultsItem?>) {
        movies.let {
            this.listMovies.clear()
            this.listMovies.addAll(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemsMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        listMovies[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(
        private val binding: ItemsMovieBinding,
        val onItemClickListener: (MovieResultsItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieResultsItem) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvRating.text = itemView.resources.getString(
                    R.string.rate, movie.voteAverage
                )
                tvDesc.text = movie.overview
                itemView.setOnClickListener { onItemClickListener(movie) }

                GlideApp.with(itemView.context)
                    .load(Constants.IMAGE_URL + movie.posterPath)
                    .fitCenter()
                    .transform(RoundedCorners(Constants.ROUND_RADIUS))
                    .error(R.drawable.ic_error)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(imgPoster)
            }
        }
    }
}