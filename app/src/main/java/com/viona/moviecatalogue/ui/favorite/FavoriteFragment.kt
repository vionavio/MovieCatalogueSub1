package com.viona.moviecatalogue.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.databinding.FavoriteFragmentBinding
import com.viona.moviecatalogue.ui.favorite.movies.FavoriteMovieActivity
import com.viona.moviecatalogue.ui.favorite.tv_shows.FavoriteTVShowActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment() {

    private var favoriteFragmentBinding: FavoriteFragmentBinding? = null
    private val binding get() = favoriteFragmentBinding

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteFragmentBinding = FavoriteFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding?.progressBarMovies?.visibility = View.VISIBLE
        binding?.progressBarTvShows?.visibility = View.VISIBLE

        viewModel.getFavoriteMovieCount().observe(viewLifecycleOwner, {
            binding?.progressBarMovies?.visibility = View.GONE
            binding?.tvMoviesCount?.text = getString(R.string.text_count_item, it)
        })
        viewModel.getFavoriteTVShowCount().observe(viewLifecycleOwner, {
            binding?.progressBarTvShows?.visibility = View.GONE
            binding?.tvTvShowCount?.text = getString(R.string.text_count_item, it)
        })

        binding?.cardMovies?.setOnClickListener {
            val intent = Intent(context, FavoriteMovieActivity::class.java)
            startActivity(intent)
        }
        binding?.cardTvShows?.setOnClickListener {
            val intent = Intent(context, FavoriteTVShowActivity::class.java)
            startActivity(intent)
        }
    }
}