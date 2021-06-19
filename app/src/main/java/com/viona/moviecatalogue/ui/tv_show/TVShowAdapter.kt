package com.viona.moviecatalogue.ui.tv_show

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.databinding.ItemsMovieBinding
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp

class TVShowAdapter(
    val context: Context,
    private val onItemClickListener: (TVShowEntity) -> Unit
) :
    PagedListAdapter<TVShowEntity, TVShowAdapter.TVShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
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
            ), onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        tvShow?.let { holder.bind(it) }
    }

    class TVShowViewHolder(
        private val binding: ItemsMovieBinding,
        val onItemClickListener: (TVShowEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TVShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvRating.text = itemView.resources.getString(
                    R.string.rate, tvShow.voteAverage
                )
                tvDesc.text = tvShow.overview
                itemView.setOnClickListener { onItemClickListener(tvShow) }

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