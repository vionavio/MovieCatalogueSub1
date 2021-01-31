package com.viona.moviecatalogue.ui.movie

import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.MovieEntity
import com.viona.moviecatalogue.databinding.ItemsMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder =
        MovieViewHolder(Items)

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {

    }s

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemsMovieBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(movie: MovieEntity) {
                    with(binding) {
                        tvItemTitle.text = movie.title
                        tvItemYear.text = itemView.resources.getString(R.string.year, movie.deadline)
                        itemView.setOnClickListener {
                            val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, movie.movieId)
                            itemView.context.startActivity(intent)
                        }
                        Glide.with(itemView.context)
                            .load(movie.imagePath)
                           /* .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                            .error(R.drawable.ic_error)*/
                            .into(imgPoster)
                    }
                }
            }
}