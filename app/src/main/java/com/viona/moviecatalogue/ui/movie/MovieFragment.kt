package com.viona.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.data.source.remote.response.movie.MovieResultsItem
import com.viona.moviecatalogue.databinding.FragmentMovieBinding
import com.viona.moviecatalogue.ui.movie.detail.DetailMovieActivity
import com.viona.moviecatalogue.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            movieViewModel.getMovie()
            movieViewModel.movies.observe(viewLifecycleOwner, { movies ->
                movieAdapter = MovieAdapter(requireContext()) { movie -> gotoResult(movie) }
                movies?.results?.let { ArrayList(it).let { it1 -> movieAdapter.setMovies(it1) } }

                fragmentMovieBinding.progressBars.root.visibility = View.GONE
                with(fragmentMovieBinding.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            })
        }
    }

    private fun gotoResult(movie: MovieResultsItem) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIE, movie.id)
        context?.startActivity(intent)
    }
}