package com.viona.moviecatalogue.ui.tv_show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowResultsItem
import com.viona.moviecatalogue.databinding.ItemsMovieBinding
import com.viona.moviecatalogue.ui.tv_show.detail.DetailTVShowActivity
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp

class TVShowAdapter :
    RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {
    private var listTVShow = ArrayList<TVShowResultsItem?>()

    fun setTVShows(tvShows: ArrayList<TVShowResultsItem?>) {
        tvShows.let {
            this.listTVShow.clear()
            this.listTVShow.addAll(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVShowViewHolder {
        return TVShowViewHolder(
            ItemsMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        listTVShow[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listTVShow.size

    class TVShowViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowResultsItem) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvRating.text = itemView.resources.getString(
                    R.string.rate, tvShow.voteAverage
                )
                tvDesc.text = tvShow.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTVShowActivity::class.java)
                    intent.putExtra(Constants.EXTRA_TV_SHOW, tvShow.id)
                    itemView.context.startActivity(intent)

                }
                GlideApp.with(itemView.context)
                    .load(Constants.IMAGE_URL + tvShow.posterPath)
                    .fitCenter()
                    .transform(RoundedCorners(Constants.ROUND_RADIUS))
                    .error(R.drawable.ic_error)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(imgPoster)
            }
        }
    }
}