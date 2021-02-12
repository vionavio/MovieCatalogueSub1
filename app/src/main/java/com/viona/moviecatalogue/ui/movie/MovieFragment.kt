package com.viona.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.databinding.FragmentMovieBinding
import com.viona.moviecatalogue.data.DataMovie


class MovieFragment : Fragment(), MovieCallback {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

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
            val viewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val movie = viewModel.getMovie()
            val movieAdapter = MovieAdapter(this)
            movieAdapter.setMovies(movie)

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onShareClick(movie: DataMovie) {
        activity?.let {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                /*  .setChooserTitle(getString(R.string.share_title))
                .setText(resources.getString(R.string.share_text, movie.title))*/
                .startChooser()
        }
    }
}