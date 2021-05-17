package com.viona.moviecatalogue.ui.tv_show.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.source.remote.response.tvShow.SeasonsItem
import com.viona.moviecatalogue.databinding.ListItemPosterBinding
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.GlideApp

class PosterAdapter(
    private val context: Context,
    private val seasonList: List<SeasonsItem?>?
) : RecyclerView.Adapter<PosterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            ListItemPosterBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup, false
            )
        )
    }

    override fun getItemCount(): Int = seasonList!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        seasonList?.get(position).let {
            if (it != null) {
                holder.bind(it)
            }
        }
    }

    inner class ViewHolder(private val item: ListItemPosterBinding) :
        RecyclerView.ViewHolder(item.root) {

        fun bind(season: SeasonsItem) {
            if (season.posterPath == null) {
                item.ivPoster.visibility = View.GONE
                item.title.visibility = View.GONE
            } else {
                GlideApp.with(itemView.context)
                    .load(Constants.IMAGE_URL + season.posterPath)
                    .transform(RoundedCorners(Constants.ROUND_RADIUS))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(item.ivPoster)
                item.title.text = season.name
                item.ivPoster.setOnClickListener {
                    Toast.makeText(context, season.name, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}