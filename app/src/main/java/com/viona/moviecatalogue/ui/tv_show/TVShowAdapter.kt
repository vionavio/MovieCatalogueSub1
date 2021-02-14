package com.viona.moviecatalogue.ui.tv_show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.databinding.ItemsMovieBinding
import com.viona.moviecatalogue.models.TVShowEntity
import com.viona.moviecatalogue.ui.tv_show.detail.DetailTVShowActivity
import com.viona.moviecatalogue.utils.GlideApp


class TVShowAdapter(private val callback: TVShowCallback) :
    RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {
    private var listTVShow = ArrayList<TVShowEntity>()

    fun setTVShows(tvShows: List<TVShowEntity>?) {
        tvShows?.let {
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

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) =
        holder.bind(listTVShow[position])

    override fun getItemCount(): Int = listTVShow.size

    class TVShowViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvRating.text = itemView.resources.getString(
                    R.string.rate, tvShow.rating
                )
                tvDesc.text = tvShow.description
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTVShowActivity::class.java)
                    intent.putExtra(DetailTVShowActivity.EXTRA_TV_SHOW, tvShow.tvShowId)
                    itemView.context.startActivity(intent)
                }
                GlideApp.with(itemView.context)
                    .load(tvShow.imagePath)
                    .fitCenter()
                    .error(R.drawable.ic_error)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(imgPoster)
            }
        }
    }
}