package com.viona.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viona.moviecatalogue.models.MovieEntity
import com.viona.moviecatalogue.databinding.ItemsMovieBinding
import com.viona.moviecatalogue.ui.movie.detail.DetailMovieActivity
import com.viona.moviecatalogue.utils.GlideApp

class MovieAdapter(private val callback: MovieCallback) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        movies?.let {
            this.listMovies.clear()
            this.listMovies.addAll(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        return MovieViewHolder(
            ItemsMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) =
        holder.bind(listMovies[position])

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemYear.text = "year"
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }
                GlideApp.with(itemView.context)
                    .load(movie.imagePath)
                    /* .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                     .error(R.drawable.ic_error)*/
                    .into(imgPoster)
            }
        }
    }
}