package com.viona.moviecatalogue.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.databinding.FragmentTvShowBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowFragment : Fragment() {

    private lateinit var fragmentTVShowBinding: FragmentTvShowBinding
    private val tvShowViewModel: TVShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTVShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            tvShowViewModel.getTVShows()

            tvShowViewModel.tvShows.observe(viewLifecycleOwner, { responseTVShows ->
                val tvShowAdapter = TVShowAdapter()
                responseTVShows?.results?.let {
                    ArrayList(it).let { it1 ->
                        tvShowAdapter.setTVShows(
                            it1
                        )
                    }
                }

                with(fragmentTVShowBinding.rvTvShow) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }

            })
        }
    }
}